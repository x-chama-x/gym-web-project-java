package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostrarEntrenamientoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int entrenamientoId = Integer.parseInt(request.getParameter("entrenamientoId"));
        try {
            // Obtener el entrenamiento por ID
            EntrenamientoDAOHardCodeado entrenamientoDAO = EntrenamientoDAOHardCodeado.getInstance();
            Entrenamiento entrenamiento = entrenamientoDAO.getById(entrenamientoId);

            // Obtener todos los datos de la tabla intermedia EntrenamientoHasEjercicio (donde se almacenan las series de los ejercicios, etc)
            EntrenamientoHasEjercicioDAOHardCodeado entrenamientoHasEjercicioDAO = EntrenamientoHasEjercicioDAOHardCodeado.getInstance();
            List<EntrenamientoHasEjercicio> datosTablaIntermedia = entrenamientoHasEjercicioDAO.getAll();

            // Filtrar los datos de la tabla intermedia por el ID del entrenamiento (para obtener los datos de ese entrenamiento)
            List<EntrenamientoHasEjercicio> datosFiltradosTablaInt = EntrenamientoHasEjercicio.filtrarPorEntrenamientoID(datosTablaIntermedia, entrenamientoId);

            // Obtener los ejercicios del entrenamiento
            List<Ejercicio> ejerciciosDeEntrenamiento = obtenerEjerciciosDeEntrenamiento(datosFiltradosTablaInt);

            // Enviar los datos al JSP
            enviarDatosAVerEntrenamiento(request, response, entrenamiento, ejerciciosDeEntrenamiento, datosFiltradosTablaInt);

        } catch (Exception e) {
            throw new ServletException("Error al mostrar el entrenamiento", e);
        }
    }

    private void enviarDatosAVerEntrenamiento(HttpServletRequest request, HttpServletResponse response, Entrenamiento entrenamiento, List<Ejercicio> ejerciciosDeEntrenamiento, List<EntrenamientoHasEjercicio> datosFiltradosTablaInt) throws ServletException, IOException {
        request.setAttribute("entrenamiento", entrenamiento);
        request.setAttribute("ejercicios", ejerciciosDeEntrenamiento);
        request.setAttribute("series", datosFiltradosTablaInt);
        request.getRequestDispatcher("WEB-INF/jsp/verEntrenamiento.jsp").forward(request, response);
    }

    private List<Ejercicio> obtenerEjerciciosDeEntrenamiento(List<EntrenamientoHasEjercicio> datosFiltradosTablaInt) throws Exception {
        EjercicioDAOHardCodeado ejercicioDAO = EjercicioDAOHardCodeado.getInstance();
        List<Ejercicio> ejerciciosDeEntrenamiento = new ArrayList<>();
        for (EntrenamientoHasEjercicio e : datosFiltradosTablaInt) {
            Ejercicio ejercicio = ejercicioDAO.getById(e.getEjercicioID());
            ejerciciosDeEntrenamiento.add(ejercicio);
        }
        return ejerciciosDeEntrenamiento;
    }
}
