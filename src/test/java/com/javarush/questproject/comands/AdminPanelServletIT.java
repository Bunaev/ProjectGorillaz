package com.javarush.questproject.comands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class AdminPanelServletIT {
    private static final String path = "/WEB-INF/jsp/admin-panel.jsp";
    @Test
    void doGetIT() throws ServletException, IOException {
        final StartServlet servlet = new StartServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(path);
        verify(dispatcher).forward(request, response);
    }
}