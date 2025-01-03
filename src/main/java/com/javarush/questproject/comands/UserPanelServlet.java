package com.javarush.questproject.comands;

import com.javarush.questproject.entity.Game;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        if (req.getParameter("editProfile")!=null) {
            req.setAttribute("user", user);
        } else if (req.getParameter("saveChangeUser")!=null) {
            questService.updateUser(user.getId(),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    user.getRole());
        } else if (req.getParameter("viewStatistics")!=null) {
            int winner = 0;
            int loose = 0;
            for (Game game : user.getGames()) {
                if (game.getStatus().equals(true)) {
                    winner++;
                } else if (game.getStatus().equals(false) && game.getEndGame().equals(true)) {
                    loose++;
                }
            }
            req.setAttribute("gameCount", user.getCountCompleteGames());
            req.setAttribute("winner", winner);
            req.setAttribute("loose", loose);
            req.setAttribute("countPages", questService.getQuestions().size());
            req.setAttribute("games", user.getGames());
        }
        if (req.getParameter("newGame") != null) {
            user.endGame(false);
            user.createGame();
            resp.sendRedirect("/game");
        } else if (req.getParameter("continueGame")!=null) {
            resp.sendRedirect("/game");
        } else if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/user-panel.jsp").forward(req, resp);
        }
    }
}
