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
import java.util.List;

@MultipartConfig
public class AgregarEjercicioServlet extends HttpServlet {
    private EjercicioDAOHardCodeado ejercicioDAOHardCodeado;
    private EquipoDAOHardCodeado equipoDAOHardCodeado;
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;

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

            Ejercicio nuevoEjercicio = new Ejercicio(0, 0, equipoID, parteDelCuerpoID, nombre, null, musculosQueTrabaja, preparacion, consejosClave, descripcion, ejecucion, musculoPrincipal, CargadoPor.SISTEMA);
            procesarImagen(filePart, nuevoEjercicio);
            ejercicioDAOHardCodeado.add(nuevoEjercicio);
            response.sendRedirect("wikiEjercicios");
        } catch (Exception e) {
            throw new ServletException("Error al agregar el ejercicio", e);
        }
    }

    // Guarda la imagen en el servidor y actualiza el nombre de la imagen en el ejercicio
    private void procesarImagen(Part filePart, Ejercicio ejercicio) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = obtenerNombreDelArchivo(filePart);
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName; // Genera un nombre de archivo único
            String filePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\" + uniqueFileName;

            // Guardar el archivo en el servidor
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            // Generar un nombre de archivo único si el archivo ya existe
            String uniqueFilePath = filePath;
            int count = 0;
            while (file.exists()) {
                count++;
                uniqueFilePath = filePath.replace(".", "_" + count + ".");
                file = new File(uniqueFilePath);
            }

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath());
            }
            ejercicio.setImagen(uniqueFileName);
        }
    }

    // Obtiene el nombre del archivo de la parte recibida
    private String obtenerNombreDelArchivo(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}