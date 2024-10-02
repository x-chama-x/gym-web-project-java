package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class EditarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;
    private ParteDelCuerpoDAO parteDelCuerpoDAO;
    private EquipoDAO equipoDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = EjercicioDAO.getInstance();
        parteDelCuerpoDAO = new ParteDelCuerpoDAO();
        equipoDAO = new EquipoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        if (ejercicioIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                List<ParteDelCuerpo> partesDelCuerpo = parteDelCuerpoDAO.getAll();
                List<Equipo> equipos = equipoDAO.getAll();

                // Obtener el nombre del equipo
                String equipoNombre = equipoDAO.getById(ejercicio.getEquipoID()).getNombre();

                request.setAttribute("ejercicio", ejercicio);
                request.setAttribute("partesDelCuerpo", partesDelCuerpo);
                request.setAttribute("equipos", equipos);
                request.setAttribute("equipoNombre", equipoNombre);
                request.getRequestDispatcher("WEB-INF/jsp/editarEjercicio.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al obtener el ejercicio", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        if (ejercicioIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                String nombre = request.getParameter("nombre");
                String musculosQueTrabaja = request.getParameter("musculosQueTrabaja");
                String preparacion = request.getParameter("preparacion");
                String consejosClave = request.getParameter("consejosClave");
                String descripcion = request.getParameter("descripcion");
                String ejecucion = request.getParameter("ejecucion");
                String musculoPrincipal = request.getParameter("musculoPrincipal");
                String imagen = "probar.jpg";

                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                ejercicio.setNombre(nombre);
                ejercicio.setMusculosQueTrabaja(musculosQueTrabaja);
                ejercicio.setPreparacion(preparacion);
                ejercicio.setConsejosClave(consejosClave);
                ejercicio.setDescripcion(descripcion);
                ejercicio.setEjecucion(ejecucion);
                ejercicio.setMusculoPrincipal(musculoPrincipal);
                ejercicio.setImagen(imagen);
                ejercicio.setEquipoID(equipoDAO.getByName(request.getParameter("equipo")).getEquipoID());
                ejercicio.setParteDelCuerpoID(parteDelCuerpoDAO.getByName(musculoPrincipal).getParteDelCuerpoID());

                ejercicioDAO.update(ejercicio);
                response.sendRedirect("mostrarDetalleDeEjercicio?ejercicioId=" + ejercicioId);
            } catch (Exception e) {
                throw new ServletException("Error al actualizar el ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio no especificado");
        }
    }
}