package com.javarush.questproject.comands;

import com.javarush.questproject.entity.User;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();
    private Integer key = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (questService.getUser((Long) req.getSession().getAttribute("id")).getProgress() != null) {
            req.setAttribute("title", questService.getQuestions().get(key).get(0));
            req.setAttribute("questions", questService.getQuestions().get(key).subList(1, questService.getQuestions().get(key).size()));
            req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
        } else {
            key = 1;
            req.setAttribute("intro", questService.getIntro());
            req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = questService.getUser((Long) req.getSession().getAttribute("id"));
        int lastGameIndex = user.getGames().size()-1;
        if (key == questService.getQuestions().size() && req.getParameter("radioButton").equals("true")) {
            user.getGames().get(lastGameIndex).setProgress();
            user.endGame(true);
            key = 1;
            req.setAttribute("winner", "true");
        } else if (req.getParameter("nextPage") != null) {
            if (req.getParameter("radioButton") != null && req.getParameter("radioButton").equals("true")) {
                key += Integer.parseInt(req.getParameter("nextPage"));
                user.getGames().get(lastGameIndex).setProgress();
            } else if (req.getParameter("radioButton") != null && req.getParameter("radioButton").equals("false")) {
                user.endGame(false);
                req.setAttribute("looser", "true");
                key = 1;
                req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
            }
            req.setAttribute("title", questService.getQuestions().get(key).get(0));
            req.setAttribute("questions", questService.getQuestions().get(key).subList(1, questService.getQuestions().get(key).size()));
        }
        if (req.getParameter("backToMainMenu") != null) {
            resp.sendRedirect("/user?id=" + user.getId());
        } else if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
        }
    }
}
