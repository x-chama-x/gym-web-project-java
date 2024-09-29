package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAO;
import com.mycompany.gym.web.project.java.modelo.Equipo;
import com.mycompany.gym.web.project.java.modelo.EquipoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MostrarDetalleDeEjercicioServlet extends HttpServlet {

    private EjercicioDAO ejercicioDAO;
    private EquipoDAO equipoDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
        equipoDAO = new EquipoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        if (ejercicioIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                List<Ejercicio> ejercicios = recuperarEjerciciosDeSesion(request);
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicios, ejercicioId);
                Equipo equipo = cargarEquipoDelEjercicio(ejercicio);
                redirigirADetalleDeEjercicio(request, response, ejercicio, equipo);
            } catch (Exception e) {
                throw new ServletException("Error al cargar los detalles del ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio no especificado");
        }
    }

    // Método que recupera los ejercicios de la sesión
    private List<Ejercicio> recuperarEjerciciosDeSesion(HttpServletRequest request) throws ServletException {
        List<Ejercicio> ejercicios = (List<Ejercicio>) request.getSession().getAttribute("ejercicios");
        if (ejercicios == null) {
            throw new ServletException("No se encontraron ejercicios en la sesión");
        }
        return ejercicios;
    }



    private Equipo cargarEquipoDelEjercicio(Ejercicio ejercicio) throws Exception {
        return equipoDAO.getById(ejercicio.getEquipoID());
    }

    private void redirigirADetalleDeEjercicio(HttpServletRequest request, HttpServletResponse response, Ejercicio ejercicio, Equipo equipo) throws ServletException, IOException {
        request.setAttribute("ejercicio", ejercicio);
        request.setAttribute("equipoNombre", equipo.getNombre());
        request.getRequestDispatcher("WEB-INF/jsp/mostrarDetalleDeEjercicio.jsp").forward(request, response);
    }
}
