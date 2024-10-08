package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;


@MultipartConfig
public class EditarEjercicioServlet extends HttpServlet {
    private EjercicioDAOHardCodeado ejercicioDAOHardCodeado;
    private EquipoDAOHardCodeado equipoDAOHardCodeado;
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;

    // inicializa el servlet y carga los ejercicios, equipos y partes del cuerpo
    @Override
    public void init() throws ServletException {
        ejercicioDAOHardCodeado = EjercicioDAOHardCodeado.getInstance();
        equipoDAOHardCodeado = new EquipoDAOHardCodeado();
        parteDelCuerpoDAOHardCodeado = new ParteDelCuerpoDAOHardCodeado();
    }

    // muestra la pagina editarEjercicio.jsp con los datos del ejercicio a editar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int ejercicioId = Integer.parseInt(request.getParameter("ejercicioId"));
            Ejercicio ejercicio = ejercicioDAOHardCodeado.getById(ejercicioId);
            ArrayList<ParteDelCuerpo> partesDelCuerpo = parteDelCuerpoDAOHardCodeado.getAll();
            ArrayList<Equipo> equipos = equipoDAOHardCodeado.getAll();
            String equipoNombre = equipoDAOHardCodeado.getById(ejercicio.getEquipoID()).getNombre();
            redirigirAeditarEjercicio(request, response, ejercicio, partesDelCuerpo, equipos, equipoNombre);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los datos para editar ejercicio", e);
        }
    }

    private void redirigirAeditarEjercicio(HttpServletRequest request, HttpServletResponse response, Ejercicio ejercicio, ArrayList<ParteDelCuerpo> partesDelCuerpo, ArrayList<Equipo> equipos, String equipoNombre) throws ServletException, IOException {
        request.setAttribute("ejercicio", ejercicio);
        request.setAttribute("partesDelCuerpo", partesDelCuerpo);
        request.setAttribute("equipos", equipos);
        request.setAttribute("equipoNombre", equipoNombre);
        request.getRequestDispatcher("WEB-INF/jsp/editarEjercicio.jsp").forward(request, response);
    }

    // edita el ejercicio con los datos recibidos y redirige a la pagina mostrarEjercicios.jsp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ejercicioId = Integer.parseInt(request.getParameter("ejercicioId"));
        String nombre = request.getParameter("nombre");
        String musculosQueTrabaja = request.getParameter("musculosQueTrabaja");
        String preparacion = request.getParameter("preparacion");
        String consejosClave = request.getParameter("consejosClave");
        String descripcion = request.getParameter("descripcion");
        String ejecucion = request.getParameter("ejecucion");
        String musculoPrincipal = request.getParameter("musculoPrincipal");
        String equipoNombre = request.getParameter("equipo");

        try {
            Ejercicio ejercicio = ejercicioDAOHardCodeado.getById(ejercicioId); // obtengo el ejercicio a editar
            int parteDelCuerpoID = parteDelCuerpoDAOHardCodeado.getByName(musculoPrincipal).getParteDelCuerpoID(); // obtengo el id de la parte del cuerpo
            int equipoID = equipoDAOHardCodeado.getByName(equipoNombre).getEquipoID(); // obtengo el id del equipo

            Part filePart = request.getPart("imagen"); // obtengo la imagen
            procesarImagen(filePart, ejercicio);
            actualizarEjercicio(ejercicio, nombre, musculosQueTrabaja, preparacion, consejosClave, descripcion, ejecucion, musculoPrincipal, parteDelCuerpoID, equipoID);
            ejercicioDAOHardCodeado.update(ejercicio); // actualizo el ejercicio en la base de datos
            response.sendRedirect("mostrarEjercicios?categoriaId=" + parteDelCuerpoID);
        } catch (Exception e) {
            throw new ServletException("Error al editar el ejercicio", e);
        }
    }

    // procesa la imagen recibida y la guarda en la carpeta assets/img
    private void procesarImagen(Part filePart, Ejercicio ejercicio) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = ejercicio.getEjercicioID() + ".jpg"; // Usar el ID del ejercicio como nombre de archivo
            String filePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\" + fileName;

            // Guardar la imagen en la carpeta assets/img
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING); // Sobrescribir el archivo existente
            }
            ejercicio.setImagen(fileName);
        }
    }

    // actualiza los datos del ejercicio
    private void actualizarEjercicio(Ejercicio ejercicio, String nombre, String musculosQueTrabaja, String preparacion, String consejosClave, String descripcion, String ejecucion, String musculoPrincipal, int parteDelCuerpoID, int equipoID) {
        ejercicio.setNombre(nombre);
        ejercicio.setMusculosQueTrabaja(musculosQueTrabaja);
        ejercicio.setPreparacion(preparacion);
        ejercicio.setConsejosClave(consejosClave);
        ejercicio.setDescripcion(descripcion);
        ejercicio.setEjecucion(ejecucion);
        ejercicio.setMusculoPrincipal(musculoPrincipal);
        ejercicio.setParteDelCuerpoID(parteDelCuerpoID);
        ejercicio.setEquipoID(equipoID);
    }
}