package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.Equipo;
import com.mycompany.gym.web.project.java.modelo.EquipoDAOHardCodeado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MostrarDetalleDeEjercicioServlet extends HttpServlet {

    private EjercicioDAOHardCodeado ejercicioDAOHardCodeado;
    private EquipoDAOHardCodeado equipoDAOHardCodeado;

    @Override
    public void init() throws ServletException {
        ejercicioDAOHardCodeado = EjercicioDAOHardCodeado.getInstance();
        equipoDAOHardCodeado = new EquipoDAOHardCodeado();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                int categoriaId = Integer.parseInt(categoriaIdStr);
                List<Ejercicio> ejercicios = ejercicioDAOHardCodeado.getByParteDelCuerpoID(categoriaId);
                Ejercicio ejercicio = ejercicioDAOHardCodeado.getById(ejercicios, ejercicioId);
                Equipo equipo = cargarEquipoDelEjercicio(ejercicio);
                redirigirADetalleDeEjercicio(request, response, ejercicio, equipo);
            } catch (Exception e) {
                throw new ServletException("Error al cargar los detalles del ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificado");
        }
    }


    private Equipo cargarEquipoDelEjercicio(Ejercicio ejercicio) throws Exception {
        return equipoDAOHardCodeado.getById(ejercicio.getEquipoID());
    }

    private void redirigirADetalleDeEjercicio(HttpServletRequest request, HttpServletResponse response, Ejercicio ejercicio, Equipo equipo) throws ServletException, IOException {
        request.setAttribute("ejercicio", ejercicio);
        request.setAttribute("equipoNombre", equipo.getNombre());
        request.getRequestDispatcher("WEB-INF/jsp/mostrarDetalleDeEjercicio.jsp").forward(request, response);
    }
}
