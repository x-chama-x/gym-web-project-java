package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpoDAOHardCodeado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MostrarCategoriasEjerciciosServlet extends HttpServlet {
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;

    @Override
    public void init() throws ServletException {
        parteDelCuerpoDAOHardCodeado = new ParteDelCuerpoDAOHardCodeado();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ParteDelCuerpo> categorias = parteDelCuerpoDAOHardCodeado.getAll();
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("WEB-INF/jsp/wikiEjercicios.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar las categorias", e);
        }
    }
}
