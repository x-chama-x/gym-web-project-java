/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gym.web.project.java.filtros;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    // Este filtro se encarga de verificar si el usuario está logueado antes de permitir el acceso a ciertas páginas
    // especificadas en el web.xml en la sección del filtro
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("userLogueado") != null) {
            // Usuario logueado, continuar con la solicitud
            chain.doFilter(httpRequest, httpResponse);
        } else {
            // Usuario no logueado, redirigir al login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}
