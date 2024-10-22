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
    private EjercicioDAOHardCodeado ejercicioDAO;


    // inicializa el servlet y carga los ejercicios
    @Override
    public void init() throws ServletException {
        ejercicioDAO = EjercicioDAOHardCodeado.getInstance();
    }


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

            // pasar los datos de la tabla intermedia de un entrenamiento a una lista de EjercicioConSeries
            List<EjercicioConSeries> ejerciciosDeEntrenamiento = obtenerEjerciciosDeEntrenamiento(datosFiltradosTablaInt);

            // Enviar los datos al JSP
            enviarDatosAVerEntrenamiento(request, response, entrenamiento, ejerciciosDeEntrenamiento);

        } catch (Exception e) {
            throw new ServletException("Error al mostrar el entrenamiento", e);
        }
    }

    private void enviarDatosAVerEntrenamiento(HttpServletRequest request, HttpServletResponse response, Entrenamiento entrenamiento, List<EjercicioConSeries> ejerciciosDeEntrenamiento) throws ServletException, IOException {
        request.setAttribute("entrenamiento", entrenamiento);
        request.setAttribute("ejerciciosConSeries", ejerciciosDeEntrenamiento);
        request.getRequestDispatcher("WEB-INF/jsp/verEntrenamiento.jsp").forward(request, response);
    }

    // Convierte los datos de la tabla intermedia en una lista de EjercicioConSeries
    private List<EjercicioConSeries> obtenerEjerciciosDeEntrenamiento(List<EntrenamientoHasEjercicio> datosFiltradosTablaInt) throws Exception {
        List<EjercicioConSeries> ejerciciosDeEntrenamiento = new ArrayList<>(); // lista de ejercicios con sus series

        // recorrer los datos de la tabla intermedia y agregarlos a la lista de ejercicios con series
        for (int i = 0; i < datosFiltradosTablaInt.size(); i++) {
            EntrenamientoHasEjercicio e = datosFiltradosTablaInt.get(i);
            EjercicioConSeries ecs = encontrarEjercicioConSeries(ejerciciosDeEntrenamiento, e.getEjercicioID());
            if (ecs == null) {
                Ejercicio ejercicio = ejercicioDAO.getById(e.getEjercicioID());
                ecs = new EjercicioConSeries(ejercicio);
                ejerciciosDeEntrenamiento.add(ecs); // si no existe el ejercicio en la lista, agregarlo
            }
            ecs.agregarSerie(e);
        }

        // setear el maximo de series de cada ejercicio
        for (EjercicioConSeries ejercicioConSeries : ejerciciosDeEntrenamiento) {
            ejercicioConSeries.setMaxSeries();
        }
        return ejerciciosDeEntrenamiento;
    }

    // Busca un ejercicio en la lista de ejercicios con series
    private EjercicioConSeries encontrarEjercicioConSeries(List<EjercicioConSeries> ejerciciosList, int ejercicioID) {
        EjercicioConSeries ecs = null;
        int i = 0;
        while (ecs == null && i < ejerciciosList.size()) {
            if (ejerciciosList.get(i).getEjercicio().getEjercicioID() == ejercicioID) {
                ecs = ejerciciosList.get(i);
            }
            i++;
        }
        return ecs;
    }
}
