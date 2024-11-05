package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.Equipo;
import com.mycompany.gym.web.project.java.modelo.EquipoDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.db.EjercicioDAO;
import com.mycompany.gym.web.project.java.modelo.db.EquipoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class MostrarDetalleDeEjercicioServlet extends HttpServlet {

    private EjercicioDAO ejercicioDAO;
    private EquipoDAO equipoDAO;

    // inicializa el servlet y carga los ejercicios y equipos
    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
        equipoDAO = new EquipoDAO();
    }

    // MostrarDetalleDeEjercicioServlet.java
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                int categoriaId = Integer.parseInt(categoriaIdStr);
                Ejercicio ejercicio = ejercicioDAO.getByIdAndParteDelCuerpoID(ejercicioId, categoriaId);
                if (ejercicio != null) {
                    Equipo equipo = cargarEquipoDelEjercicio(ejercicio);
                    redirigirADetalleDeEjercicio(request, response, ejercicio, equipo);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ejercicio no encontrado para la categoría especificada");
                }
            } catch (Exception e) {
                throw new ServletException("Error al cargar los detalles del ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificado");
        }
    }

    private Equipo cargarEquipoDelEjercicio(Ejercicio ejercicio) throws Exception {
        return equipoDAO.getById(ejercicio.getEquipoID());
    }

    // redirige a la pagina mostrarDetalleDeEjercicio.jsp
    private void redirigirADetalleDeEjercicio(HttpServletRequest request, HttpServletResponse response, Ejercicio ejercicio, Equipo equipo) throws ServletException, IOException {
        request.setAttribute("ejercicio", ejercicio); // establezco el ejercicio como atributo de la request (solicitud)
        request.setAttribute("equipoNombre", equipo.getNombre()); // establezco el nombre del equipo como atributo de la request (solicitud)
        request.getRequestDispatcher("WEB-INF/jsp/mostrarDetalleDeEjercicio.jsp").forward(request, response);
    }
}
