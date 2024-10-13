package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.db.EjercicioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

public class EliminarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;

    // inicializa el servlet y carga los ejercicios
    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
    }

    // se encarga de obtener el ejercicio y redirigir a la página de confirmación de eliminación
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId"); // obtengo el id del ejercicio
        String categoriaIdStr = request.getParameter("categoriaId"); // obtengo el id de la categoría
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId); // obtengo el ejercicio
                establecerAtributosYReenviarSolicitud(request, response, ejercicioIdStr, categoriaIdStr, ejercicio);
            } catch (Exception e) {
                throw new ServletException("Error al obtener el ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificada");
        }
    }

    // redirige a la página de confirmación de eliminación de ejercicio
    private void establecerAtributosYReenviarSolicitud(HttpServletRequest request, HttpServletResponse response, String ejercicioIdStr, String categoriaIdStr, Ejercicio ejercicio) throws ServletException, IOException {
        request.setAttribute("ejercicioId", ejercicioIdStr);
        request.setAttribute("categoriaId", categoriaIdStr);
        request.setAttribute("ejercicioNombre", ejercicio.getNombre());
        request.setAttribute("ejercicioImagen", ejercicio.getImagen());
        request.getRequestDispatcher("WEB-INF/jsp/confirmarEliminarEjercicio.jsp").forward(request, response);
    }

    // elimina el ejercicio y redirige a la página de ejercicios de la categoría
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        String categoriaIdStr = request.getParameter("categoriaId");
        if (ejercicioIdStr != null && categoriaIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                int categoriaId = Integer.parseInt(categoriaIdStr);
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                eliminarImagen(ejercicio.getImagen()); // elimino la imagen del ejercicio
                ejercicioDAO.delete(ejercicioId); // elimino el ejercicio
                response.sendRedirect("mostrarEjercicios?categoriaId=" + categoriaId);
            } catch (Exception e) {
                throw new ServletException("Error al eliminar el ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio o categoría no especificada");
        }
    }

    // elimina la imagen del ejercicio del directorio
    private void eliminarImagen(String imagen) {
        String imagePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\ejercicioImagen\\" + imagen;
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }


}
