package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
import com.mycompany.gym.web.project.java.modelo.db.EjercicioDAO;
import com.mycompany.gym.web.project.java.modelo.db.EquipoDAO;
import com.mycompany.gym.web.project.java.modelo.db.ParteDelCuerpoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@MultipartConfig
public class AgregarEjercicioServlet extends HttpServlet {
    private EjercicioDAO ejercicioDAO;
    private EquipoDAO equipoDAO;
    private ParteDelCuerpoDAO parteDelCuerpoDAO;

    // Inicializa el servlet y carga los ejercicios, equipos y partes del cuerpo
    @Override
    public void init() throws ServletException {
        ejercicioDAO = new EjercicioDAO();
        equipoDAO = new EquipoDAO();
        parteDelCuerpoDAO = new ParteDelCuerpoDAO();
    }

    // Muestra la pagina agregarEjercicio.jsp con los datos de las partes del cuerpo y equipos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ParteDelCuerpo> partesDelCuerpo = parteDelCuerpoDAO.getAll();
            List<Equipo> equipos = equipoDAO.getAll();
            request.setAttribute("partesDelCuerpo", partesDelCuerpo);
            request.setAttribute("equipos", equipos);
            request.getRequestDispatcher("WEB-INF/jsp/agregarEjercicio.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los datos para agregar ejercicio", e);
        }
    }

    // Agrega un nuevo ejercicio con los datos recibidos y redirige a la pagina wikiEjercicios.jsp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        Part filePart = request.getPart("imagen");

        try {
            String musculosQueTrabaja = request.getParameter("musculosQueTrabaja");
            String preparacion = request.getParameter("preparacion");
            String consejosClave = request.getParameter("consejosClave");
            String descripcion = request.getParameter("descripcion");
            String ejecucion = request.getParameter("ejecucion");
            String musculoPrincipal = request.getParameter("musculoPrincipal");
            String equipoNombre = request.getParameter("equipo");

            int parteDelCuerpoID = parteDelCuerpoDAO.getByName(musculoPrincipal).getParteDelCuerpoID();
            int equipoID = equipoDAO.getByName(equipoNombre).getEquipoID();

            // Obtener el ID del usuario de la sesión
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("userLogueado");
            int usuarioID = usuario != null ? usuario.getUsuarioID() : 0;

            CargadoPor cargadoPor = determinarOrigen(request); // Determina si el ejercicio fue cargado por el sistema o un usuario

            Ejercicio nuevoEjercicio = new Ejercicio(0, usuarioID, equipoID, parteDelCuerpoID, nombre, "PorDefecto", musculosQueTrabaja, preparacion, consejosClave, descripcion, ejecucion, musculoPrincipal, cargadoPor);
            ejercicioDAO.add(nuevoEjercicio); // Agregar primero para obtener el ID del ejercicio
            String imagenNombre = procesarImagen(filePart, nuevoEjercicio); // Procesar la imagen del ejercicio
            nuevoEjercicio.setImagen(imagenNombre); // Actualizar el nombre de la imagen en el objeto Ejercicio
            ejercicioDAO.update(nuevoEjercicio); // Actualizar el ejercicio con el nombre de la imagen
            response.sendRedirect("wikiEjercicios");
        } catch (Exception e) {
            throw new ServletException("Error al agregar el ejercicio", e);
        }
    }

    private String procesarImagen(Part filePart, Ejercicio ejercicio) throws IOException {
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = ejercicio.getEjercicioID() + ".jpg"; // Usar el ID del ejercicio como nombre de archivo
            String filePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\ejercicioImagen\\" + fileName;

            // Guardar el archivo en el servidor
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath());
            }
            ejercicio.setImagen(fileName);
        }
        return fileName;
    }


    // Metodo para determinar el origen del ejercicio basado en el rol del usuario
    private CargadoPor determinarOrigen(HttpServletRequest request) {
        CargadoPor cargadoPor = CargadoPor.USUARIO;
        HttpSession session = request.getSession(); // Obtener la sesion del usuario
        String rolUsuario = (String) session.getAttribute("rolUsuario"); // Obtener el rol del usuario
        if (rolUsuario != null && rolUsuario.equals(RolUsuario.ADMINISTRADOR.name())) {
            cargadoPor = CargadoPor.SISTEMA;
        }
        return cargadoPor;
    }
}