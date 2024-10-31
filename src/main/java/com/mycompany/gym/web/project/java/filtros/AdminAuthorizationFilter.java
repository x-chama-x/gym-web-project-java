package com.mycompany.gym.web.project.java.filtros;

import com.mycompany.gym.web.project.java.modelo.Usuario;
import com.mycompany.gym.web.project.java.modelo.RolUsuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminAuthorizationFilter implements Filter {

    // verificar si el usuario es administrador antes de permitir el acceso a ciertas páginas especificadas en el web.xml en la sección del filtro
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("userLogueado") != null) {
            Usuario userLogueado = (Usuario) session.getAttribute("userLogueado");
            if (userLogueado.getRol() == RolUsuario.ADMINISTRADOR) {
                // Es administrador, continuar con la solicitud
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/perfil");
            }
        }
    }
}