package com.javarush.questproject.comands;

import com.javarush.questproject.config.Summer;
import com.javarush.questproject.entity.Game;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user")
public class UserPanelServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = questService.getUser((Long) req.getSession().getAttribute("id"));
        req.getSession().setAttribute("progress", user.getProgress());
        if (!user.getGames().isEmpty()) {
            req.getSession().setAttribute("countGames", user.getCountCompleteGames());
        }
        req.getRequestDispatcher("/WEB-INF/jsp/user-panel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = questService.getUser((Long) req.getSession().getAttribute("id"));
        HttpSession session = req.getSession();
        if (req.getParameter("editProfile")!=null) {
            Summer.removeAttributesInSession(session, "games", "countPages", "loose", "winner", "gameCount");
            session.setAttribute("user", user);
        } else if (req.getParameter("saveChangeUser")!=null) {
            questService.updateUser(user.getId(),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    user.getRole());
            Summer.removeAttributesInSession(session, "user");
        } else if (req.getParameter("viewStatistics")!=null) {
            Summer.removeAttributesInSession(session, "user");
            int winner = 0;
            int loose = 0;
            for (Game game : user.getGames()) {
                if (game.getStatus().equals(true)) {
                    winner++;
                } else if (game.getStatus().equals(false) && game.getEndGame().equals(true)) {
                    loose++;
                }
            }
            session.setAttribute("gameCount", user.getCountCompleteGames());
            session.setAttribute("winner", winner);
            session.setAttribute("loose", loose);
            session.setAttribute("countPages", questService.getQuestions().size());
            session.setAttribute("games", user.getGames());
        }
        if (req.getParameter("newGame") != null) {
            user.endGame(false);
            Summer.removeAttributesInSession(session, "winner");
            session.setAttribute("newGame", true);
            user.createGame();
            resp.sendRedirect("/game");
        } else if (req.getParameter("continueGame")!=null) {
            resp.sendRedirect("/game");
        } else if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/user?id=" + user.getId());
        }
    }
}
