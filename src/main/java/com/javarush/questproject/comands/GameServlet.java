package com.javarush.questproject.comands;

import com.javarush.questproject.config.Summer;

import com.javarush.questproject.entity.User;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();
    private Integer key = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("newGame")!=null) {
            key = 0;
            Summer.removeAttributesInSession(session,"newGame");
        }
        if (key == 0) {
            req.setAttribute("intro", questService.getIntro());
        } else if (key > 0 && session.getAttribute("winner") == null) {
            session.setAttribute("title", questService.getTitle(key));
            session.setAttribute("questions", questService.getContentInPage(key));
        } else if (key > 0 && session.getAttribute("winner") != null) {
            Summer.removeAttributesInSession(session, "title", "questions", "intro");
            key = 0;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = questService.getUser((Long) req.getSession().getAttribute("id"));
        int lastGameIndex = user.getGames().size()-1;
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            key += Integer.parseInt(req.getParameter("nextPage"));
            if (key > questService.getQuestions().size() && req.getParameter("radioButton").equals("true")) {
                user.getGames().get(lastGameIndex).setProgress();
                user.endGame(true);
                session.setAttribute("winner", "true");
            } else if (req.getParameter("radioButton") != null && req.getParameter("radioButton").equals("true")) {
                user.getGames().get(lastGameIndex).setProgress();
            } else if (req.getParameter("radioButton") != null && req.getParameter("radioButton").equals("false")) {
                user.endGame(false);
                session.setAttribute("winner", "false");
            }
        }
        if (req.getParameter("backToMainMenu") != null) {
            Summer.removeAttributesInSession(session,"games", "user");
            resp.sendRedirect("/user?id=" + user.getId());
        } else if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/game");
        }
    }
}



