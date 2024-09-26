package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAO;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class mostrarEjerciciosDeCategoriaServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;
    private ParteDelCuerpoDAO parteDelCuerpoDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
        parteDelCuerpoDAO = new ParteDelCuerpoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoriaIdStr = request.getParameter("categoriaId");
        if (categoriaIdStr != null) {
            try {
                int categoriaId = Integer.parseInt(categoriaIdStr);
                List<Ejercicio> ejercicios = ejercicioDAO.getAll(categoriaId);
                ParteDelCuerpo categoria = parteDelCuerpoDAO.getById(categoriaId);
                request.setAttribute("ejercicios", ejercicios);
                request.setAttribute("categoriaNombre", categoria.getNombre());
                request.getRequestDispatcher("WEB-INF/jsp/mostrarEjercicios.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al cargar los ejercicios", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoría no especificada");
        }
    }
}
