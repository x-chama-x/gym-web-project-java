package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
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
    private EjercicioDAOHardCodeado ejercicioDAOHardCodeado;
    private EquipoDAOHardCodeado equipoDAOHardCodeado;
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;

    // Inicializa el servlet y carga los ejercicios, equipos y partes del cuerpo
    @Override
    public void init() throws ServletException {
        ejercicioDAOHardCodeado = EjercicioDAOHardCodeado.getInstance();
        equipoDAOHardCodeado = new EquipoDAOHardCodeado();
        parteDelCuerpoDAOHardCodeado = new ParteDelCuerpoDAOHardCodeado();
    }

    // Muestra la pagina agregarEjercicio.jsp con los datos de las partes del cuerpo y equipos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ParteDelCuerpo> partesDelCuerpo = parteDelCuerpoDAOHardCodeado.getAll();
            List<Equipo> equipos = equipoDAOHardCodeado.getAll();
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

            int parteDelCuerpoID = parteDelCuerpoDAOHardCodeado.getByName(musculoPrincipal).getParteDelCuerpoID();
            int equipoID = equipoDAOHardCodeado.getByName(equipoNombre).getEquipoID();

            // Obtener el ID del usuario de la sesión
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int usuarioID = usuario != null ? usuario.getUsuarioID() : 0;

            CargadoPor cargadoPor = determinarOrigen(request); // Determina si el ejercicio fue cargado por el sistema o un usuario

            Ejercicio nuevoEjercicio = new Ejercicio(0, usuarioID, equipoID, parteDelCuerpoID, nombre, null, musculosQueTrabaja, preparacion, consejosClave, descripcion, ejecucion, musculoPrincipal, cargadoPor);
            ejercicioDAOHardCodeado.add(nuevoEjercicio); // Agregar primero para obtener el ID del ejercicio

            procesarImagen(filePart, nuevoEjercicio);
            ejercicioDAOHardCodeado.update(nuevoEjercicio); // actualizo el ejercicio en la base de datos
            response.sendRedirect("wikiEjercicios");
        } catch (Exception e) {
            throw new ServletException("Error al agregar el ejercicio", e);
        }
    }

    // Guarda la imagen en el servidor y actualiza el nombre de la imagen en el ejercicio
    private void procesarImagen(Part filePart, Ejercicio ejercicio) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = ejercicio.getEjercicioID() + ".jpg"; // Usar el ID del ejercicio como nombre de archivo
            String filePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\" + fileName;

            // Guardar el archivo en el servidor
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath());
            }
            ejercicio.setImagen(fileName);
        }
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