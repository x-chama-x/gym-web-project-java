package com.mycompany.gym.web.project.java.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    // este metodo es para cerrar la sesion del usuario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate(); // Invalida la sesión del usuario
        response.sendRedirect(request.getContextPath()); // Redirige a la página principal (index.jsp)
    }
}
