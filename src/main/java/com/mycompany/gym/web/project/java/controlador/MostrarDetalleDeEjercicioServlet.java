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
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                int categoriaId = Integer.parseInt(categoriaIdStr);
                ejercicioDAO.getAll(categoriaId); // Cargar ejercicios por categoría antes de buscar el ejercicio específico
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                Equipo equipo = equipoDAO.getById(ejercicio.getEquipoID()); // Cargar el equipo del ejercicio según su equipoID
                request.setAttribute("ejercicio", ejercicio);
                request.setAttribute("equipoNombre", equipo.getNombre());
                request.getRequestDispatcher("WEB-INF/jsp/mostrarDetalleDeEjercicio.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al cargar los detalles del ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificada");
        }
    }
}
