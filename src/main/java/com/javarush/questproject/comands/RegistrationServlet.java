package com.javarush.questproject.comands;

import com.javarush.questproject.config.Summer;
import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!questService.getUsers().stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(req.getParameter("login")))) {
            Summer.removeAttributesInSession(req.getSession(), "error");
            User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"));
            user.setRole(Role.USER);
            questService.addUser(user);
            resp.sendRedirect("/login");
        } else {
            req.getSession().setAttribute("error", "Пользователь с таким логином уже существует.");
            resp.sendRedirect("/registration");
        }
    }
}
