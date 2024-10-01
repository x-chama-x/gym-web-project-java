package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.EjercicioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EliminarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = EjercicioDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            request.setAttribute("ejercicioId", ejercicioIdStr);
            request.setAttribute("categoriaId", categoriaIdStr);
            request.getRequestDispatcher("WEB-INF/jsp/confirmarEliminarEjercicio.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificada");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                int categoriaId = Integer.parseInt(categoriaIdStr);
                ejercicioDAO.delete(ejercicioId);
                response.sendRedirect("mostrarEjercicios?categoriaId=" + categoriaId);
            } catch (Exception e) {
                throw new ServletException("Error al eliminar el ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificada");
        }
    }



}
