package com.javarush.questproject.comands;

import com.javarush.questproject.config.Summer;
import com.javarush.questproject.entity.Role;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {
    private final QuestService questService = QuestService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("roles", Role.values());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin-panel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            if (req.getParameter("allUsers") != null) {
                Summer.removeAttributesInSession(session, "quest", "page", "title", "pageContent", "user");
                session.setAttribute("users", questService.getUsers());
            } else if (req.getParameter("user") != null) {
                Long id = Long.valueOf(req.getParameter("user"));
                session.setAttribute("user", questService.getUser(id));
            } else if (req.getParameter("saveChangeUser") != null) {
                questService.updateUser(Long.valueOf(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("password"),
                        Role.valueOf(req.getParameter("role")));
                Summer.removeAttributesInSession(session, "user");
            } else if (req.getParameter("questEdit") != null) {
                Summer.removeAttributesInSession(session, "users", "user", "title", "page", "pageContent");
                session.setAttribute("quest", questService.getQuestions());
            } else if (req.getParameter("page") != null) {
                session.setAttribute("page", req.getParameter("page"));
                session.setAttribute("title", questService.getTitle(session.getAttribute("page")));
                session.setAttribute("pageContent", questService.getContentInPage(session.getAttribute("page")));
            } else if (req.getParameter("deletePage") != null) {
                questService.deletePage(req.getParameter("deletePage"));
                Summer.removeAttributesInSession(session, "title", "pageContent");
            } else if (req.getParameter("addPage") != null) {
                questService.addPage();
            } else if (req.getParameter("addQuestion") != null) {
                String title = req.getParameter("textAreaTitle");
                String[] content = req.getParameterValues("textAreaContent");
                String[] status = req.getParameterValues("statusQuestion");
                String numberPage = (String) req.getSession().getAttribute("page");
                questService.savePage(numberPage, title, content, status);
                questService.addQuestion(Integer.parseInt(req.getParameter("addQuestion")));
                session.setAttribute("pageContent", questService.getContentInPage(session.getAttribute("page")));
            } else if (req.getParameter("deleteQuestion") != null) {
                questService.deleteQuestion((String) session.getAttribute("page"), req.getParameter("deleteQuestion"));
                session.setAttribute("title", questService.getTitle(session.getAttribute("page")));
                session.setAttribute("pageContent", questService.getContentInPage(session.getAttribute("page")));
            } else if (req.getParameter("savePage") != null) {
                String title = req.getParameter("textAreaTitle");
                String[] content = req.getParameterValues("textAreaContent");
                String[] status = req.getParameterValues("statusQuestion");
                String numberPage = (String) req.getSession().getAttribute("page");
                questService.savePage(numberPage, title, content, status);
                session.setAttribute("title", questService.getTitle(session.getAttribute("page")));
                session.setAttribute("pageContent", questService.getContentInPage(session.getAttribute("page")));
            }
            resp.sendRedirect("/admin");
        }
    }
}
