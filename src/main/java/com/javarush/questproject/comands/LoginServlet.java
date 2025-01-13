package com.javarush.questproject.comands;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
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
        User user = questService.getUser(login, password);
        HttpSession session = req.getSession();
        if (req.getParameter("enter") != null) {
            if (user != null && user.getRole().equals(Role.ADMIN)) {
                session.setAttribute("authorized", true);
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute("id", user.getId());
                resp.sendRedirect("/admin");
            } else if (user != null && user.getRole().equals(Role.USER)) {
                session.setAttribute("authorized", true);
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute("id", user.getId());
                resp.sendRedirect("/user?id=" + user.getId());
            } else if (user != null && user.getRole().equals(Role.BANNED)) {
                session.setAttribute("authorized", true);
                session.setAttribute("banned", true);
                resp.sendRedirect("/login");
            } else {
                session.setAttribute("authorized", false);
                resp.sendRedirect("/login");
            }
        }
    }
}