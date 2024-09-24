package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostrarCategoriasEjerciciosServlet extends HttpServlet {
    private List<ParteDelCuerpo> categorias;


    // inicializar el servlet y crea una lista de categorías (ParteDelCuerpo)
    @Override
    public void init() throws ServletException {
        categorias = new ArrayList<>();
        categorias.add(new ParteDelCuerpo(1, "Pecho"));
        categorias.add(new ParteDelCuerpo(2, "Espalda"));
        categorias.add(new ParteDelCuerpo(3, "Piernas"));
        categorias.add(new ParteDelCuerpo(4, "Hombros"));
        categorias.add(new ParteDelCuerpo(5, "Brazos"));
        categorias.add(new ParteDelCuerpo(6, "Abdominales"));
    }


    // establece las categorías como un atributo de la solicitud y reenvía la solicitud a wikiEjercicios.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("WEB-INF/jsp/wikiEjercicios.jsp").forward(request, response);
    }
}
