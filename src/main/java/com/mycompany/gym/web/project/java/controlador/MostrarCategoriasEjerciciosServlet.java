package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpoDAOHardCodeado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class MostrarCategoriasEjerciciosServlet extends HttpServlet {
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;


    // inicializa el servlet y carga las categorias
    @Override
    public void init() throws ServletException {
        parteDelCuerpoDAOHardCodeado = new ParteDelCuerpoDAOHardCodeado();
    }

    // muestra las categorias en la pagina wikiEjercicios.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<ParteDelCuerpo> categorias = new ArrayList<>(parteDelCuerpoDAOHardCodeado.getAll()); // obtengo las categorias cargadas en el DAO
            request.setAttribute("categorias", categorias); // establezco las categorias como atributo de la request (solicitud)
            request.getRequestDispatcher("WEB-INF/jsp/wikiEjercicios.jsp").forward(request, response); // se reenvia la solicitud a la pagina wikiEjercicios.jsp
        } catch (Exception e) {
            throw new ServletException("Error al cargar las categorias", e); // si ocurre un error se lanza una excepcion
        }
    }
}
