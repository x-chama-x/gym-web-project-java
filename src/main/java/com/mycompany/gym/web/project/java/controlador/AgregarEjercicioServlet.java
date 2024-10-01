package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AgregarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;
    private EquipoDAO equipoDAO;
    private ParteDelCuerpoDAO parteDelCuerpoDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
        equipoDAO = new EquipoDAO();
        parteDelCuerpoDAO = new ParteDelCuerpoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/agregarEjercicio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        // String imagen = request.getParameter("imagen"); // por ahora no puedo cargar la imagen
        String imagen = "probar.jpg";
        String musculosQueTrabaja = request.getParameter("musculosQueTrabaja");
        String preparacion = request.getParameter("preparacion");
        String consejosClave = request.getParameter("consejosClave");
        String descripcion = request.getParameter("descripcion");
        String ejecucion = request.getParameter("ejecucion");
        String musculoPrincipal = request.getParameter("musculoPrincipal");
        String equipoNombre = request.getParameter("equipo");

        try {
            int parteDelCuerpoID = parteDelCuerpoDAO.getByName(musculoPrincipal).getParteDelCuerpoID();
            int equipoID = equipoDAO.getByName(equipoNombre).getEquipoID();

            Ejercicio nuevoEjercicio = new Ejercicio(0, 0, equipoID, parteDelCuerpoID, nombre, imagen, musculosQueTrabaja, preparacion, consejosClave, descripcion, ejecucion, musculoPrincipal, CargadoPor.SISTEMA);
            ejercicioDAO.add(nuevoEjercicio);
            System.out.println(nuevoEjercicio);
            response.sendRedirect("wikiEjercicios");
        } catch (Exception e) {
            throw new ServletException("Error al agregar el ejercicio", e);
        }
    }
}