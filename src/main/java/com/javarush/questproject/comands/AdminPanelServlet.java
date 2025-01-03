package com.javarush.questproject.comands;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.model.QuestService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        if (req.getParameter("allUsers") != null) {
            req.setAttribute("users", questService.getUsers());
        } else if (req.getParameter("user") != null) {
            req.setAttribute("user", questService.getUser(Long.parseLong(req.getParameter("user"))));
            req.setAttribute("users", questService.getUsers());
        } else if (req.getParameter("saveChangeUser")!=null) {
            questService.updateUser(Long.parseLong(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"),
                    Role.valueOf(req.getParameter("role")));
            req.setAttribute("users", questService.getUsers());
        } else if (req.getParameter("questEdit") != null) {
            req.setAttribute("quest", questService.getQuestions());
        } else if (req.getParameter("page")!=null) {
            req.getSession().setAttribute("page", req.getParameter("page"));
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("quest", questService.getQuestions());
            req.setAttribute("title", questService.getQuestions().get(Integer.parseInt(req.getParameter("page"))).get(0).getContent());
            req.setAttribute("pageContent", questService.getQuestions().get(Integer.parseInt(req.getParameter("page"))).subList(1, questService.getQuestions().get(Integer.parseInt(req.getParameter("page"))).size()));
        } else if (req.getParameter("deletePage")!=null) {
            questService.deletePage(Integer.parseInt(req.getParameter("deletePage")));
            req.setAttribute("quest", questService.getQuestions());
        } else if (req.getParameter("addPage")!=null) {
            questService.addPage();
            req.setAttribute("quest", questService.getQuestions());
        } else if (req.getParameter("addQuestion")!=null) {
            String title = req.getParameter("textAreaTitle");
            String [] content = req.getParameterValues("textAreaContent");
            String [] status = req.getParameterValues("statusQuestion");
            String numberPage = (String) req.getSession().getAttribute("page");
            questService.savePage(numberPage, title, content, status);
            questService.addQuestion(Integer.parseInt(req.getParameter("addQuestion")));
            req.setAttribute("quest", questService.getQuestions());
            req.setAttribute("title", questService.getQuestions().get(Integer.parseInt(req.getParameter("addQuestion"))).get(0).getContent());
            req.setAttribute("pageContent", questService.getQuestions().get(Integer.parseInt(req.getParameter("addQuestion"))).subList(1, questService.getQuestions().get(Integer.parseInt(req.getParameter("addQuestion"))).size()));
        } else if (req.getParameter("deleteQuestion")!=null) {
            questService.deleteQuestion((String) req.getSession().getAttribute("page"), req.getParameter("deleteQuestion"));
            req.setAttribute("quest", questService.getQuestions());
            req.setAttribute("title", questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).get(0).getContent());
            req.setAttribute("pageContent", questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).subList(1, questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).size()));
        } else if (req.getParameter("savePage")!=null) {
            String title = req.getParameter("textAreaTitle");
            String [] content = req.getParameterValues("textAreaContent");
            String [] status = req.getParameterValues("statusQuestion");
            String numberPage = (String) req.getSession().getAttribute("page");
            questService.savePage(numberPage, title, content, status);
            req.setAttribute("quest", questService.getQuestions());
            req.setAttribute("title", questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).get(0).getContent());
            req.setAttribute("pageContent", questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).subList(1, questService.getQuestions().get(Integer.parseInt((String) req.getSession().getAttribute("page"))).size()));
        }
        if (req.getParameter("exit") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/admin-panel.jsp").forward(req, resp);
        }
    }
}
