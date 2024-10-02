package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Ejercicio;
import com.mycompany.gym.web.project.java.modelo.EjercicioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@MultipartConfig
public class EditarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;

    @Override
    public void init() throws ServletException {
        ejercicioDAO = EjercicioDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        if (ejercicioIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                request.setAttribute("ejercicio", ejercicio);
                request.getRequestDispatcher("WEB-INF/jsp/editarEjercicio.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al obtener el ejercicio", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ejercicio no especificado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ejercicioIdStr = request.getParameter("ejercicioId");
        if (ejercicioIdStr != null) {
            try {
                int ejercicioId = Integer.parseInt(ejercicioIdStr);
                String nombre = request.getParameter("nombre");
                Part imagenPart = request.getPart("imagen");
                String musculosQueTrabaja = request.getParameter("musculosQueTrabaja");
                String preparacion = request.getParameter("preparacion");
                String consejosClave = request.getParameter("consejosClave");
                String descripcion = request.getParameter("descripcion");
                String ejecucion = request.getParameter("ejecucion");
                String musculoPrincipal = request.getParameter("musculoPrincipal");

                Ejercicio ejercicio = ejercicioDAO.getById(ejercicioId);
                ejercicio.setNombre(nombre);
                if (imagenPart != null && imagenPart.getSize() > 0) {
                    String fileName = imagenPart.getSubmittedFileName();
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "img";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    imagenPart.write(uploadPath + File.separator + fileName);
                    ejercicio.setImagen(fileName);
                }
                ejercicio.setMusculosQueTrabaja(musculosQueTrabaja);
                ejercicio.setPreparacion(preparacion);
                ejercicio.setConsejosClave(consejosClave);
                ejercicio.setDescripcion(descripcion);
                ejercicio.setEjecucion(ejecucion);
                ejercicio.setMusculoPrincipal(musculoPrincipal);

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