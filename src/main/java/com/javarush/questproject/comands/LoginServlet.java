package com.javarush.questproject.comands;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (req.getParameter("enter") != null) {
            if (questService.authorize(login, password) && questService.getUser(questService.getUserId(login, password)).getRole().equals(Role.ADMIN)) {
                req.setAttribute("authorized", true);
                HttpSession session = req.getSession(true);
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute("id", questService.getUserId(login, password));
                resp.sendRedirect("/admin");
            } else if (questService.authorize(login, password) && questService.getUser(questService.getUserId(login, password)).getRole().equals(Role.USER)) {
                req.setAttribute("authorized", true);
                HttpSession session = req.getSession(true);
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute("id", questService.getUserId(login, password));
                resp.sendRedirect("/user?id=" + questService.getUserId(login, password));
            } else if (questService.authorize(login, password) && questService.getUser(questService.getUserId(login, password)).getRole().equals(Role.BANNED)) {
                req.setAttribute("authorized", true);
                req.setAttribute("banned", true);
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("authorized", false);
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            }
        }
    }
}