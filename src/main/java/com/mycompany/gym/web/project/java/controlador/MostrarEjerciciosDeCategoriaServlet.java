package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpoDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.db.EjercicioDAO;
import com.mycompany.gym.web.project.java.modelo.db.ParteDelCuerpoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostrarEjerciciosDeCategoriaServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;
    private ParteDelCuerpoDAO parteDelCuerpoDAO;

    // inicializa el servlet y carga los DAOs de ejercicios y partes del cuerpo
    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO(); // cargar los ejercicios
        parteDelCuerpoDAO = new ParteDelCuerpoDAO(); // cargar las partes del cuerpo (categorías)
    }

    // obtiene los ejercicios de una categoría y redirige a la vista de ejercicios (mostrarEjercicios.jsp)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoriaIdStr = request.getParameter("categoriaId"); // obtener el ID de la categoría de la petición/solicitud
        if (categoriaIdStr != null) {
            try {
                int categoriaId = Integer.parseInt(categoriaIdStr); // convertir el ID de la categoría a entero
                ArrayList<Ejercicio> ejercicios = ejercicioDAO.getByParteDelCuerpoID(categoriaId); // obtener los ejercicios de la categoría
                ParteDelCuerpo categoria = cargarCategoria(categoriaId); // obtener la categoría
                redirigirAListaDeEjercicios(request, response, ejercicios, categoria);
            } catch (Exception e) {
                throw new ServletException("Error al cargar los ejercicios", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoría no especificada");
        }
    }

    // carga una categoría por su ID
    private ParteDelCuerpo cargarCategoria(int categoriaId) throws Exception {
        return parteDelCuerpoDAO.getById(categoriaId);
    }

    // redirige a la vista de ejercicios (mostrarEjercicios.jsp)
    private void redirigirAListaDeEjercicios(HttpServletRequest request, HttpServletResponse response, List<Ejercicio> ejercicios, ParteDelCuerpo categoria) throws ServletException, IOException {
        request.setAttribute("ejercicios", ejercicios); // establezco los ejercicios como atributo de la petición
        request.setAttribute("categoriaNombre", categoria.getNombre()); // establezco el nombre de la categoría como atributo de la petición
        request.getRequestDispatcher("WEB-INF/jsp/mostrarEjercicios.jsp").forward(request, response);
    }
}