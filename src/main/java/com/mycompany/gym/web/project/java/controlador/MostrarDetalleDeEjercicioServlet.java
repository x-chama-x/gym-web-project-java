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

    // muestra los detalles del ejercicio en la pagina mostrarDetalleDeEjercicio.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId"); // obtengo el id del ejercicio de la solicitud
        // String categoriaIdStr = request.getParameter("categoriaId"); // obtengo el id de la categoria de la solicitud (solo para datos hardcodeados)
        if (ejercicioIdStr != null /*&& categoriaIdStr != null*/) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                // int categoriaId = Integer.parseInt(categoriaIdStr); // solo para datos hardcodeados
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId); // obtengo el ejercicio por su id
                Equipo equipo = cargarEquipoDelEjercicio(ejercicio); // obtengo el equipo del ejercicio
                redirigirADetalleDeEjercicio(request, response, ejercicio, equipo);
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
