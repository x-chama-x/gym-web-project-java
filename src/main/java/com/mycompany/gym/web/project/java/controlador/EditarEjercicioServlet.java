package com.mycompany.gym.web.project.java.controlador;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.mycompany.gym.web.project.java.modelo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@MultipartConfig
public class EditarEjercicioServlet extends HttpServlet {
    private EjercicioDAOHardCodeado ejercicioDAOHardCodeado;
    private EquipoDAOHardCodeado equipoDAOHardCodeado;
    private ParteDelCuerpoDAOHardCodeado parteDelCuerpoDAOHardCodeado;

    @Override
    public void init() throws ServletException {
        ejercicioDAOHardCodeado = EjercicioDAOHardCodeado.getInstance();
        equipoDAOHardCodeado = new EquipoDAOHardCodeado();
        parteDelCuerpoDAOHardCodeado = new ParteDelCuerpoDAOHardCodeado();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int ejercicioId = Integer.parseInt(request.getParameter("ejercicioId"));
            Ejercicio ejercicio = ejercicioDAOHardCodeado.getById(ejercicioId);
            List<ParteDelCuerpo> partesDelCuerpo = parteDelCuerpoDAOHardCodeado.getAll();
            List<Equipo> equipos = equipoDAOHardCodeado.getAll();
            request.setAttribute("ejercicio", ejercicio);
            request.setAttribute("partesDelCuerpo", partesDelCuerpo);
            request.setAttribute("equipos", equipos);
            request.setAttribute("equipoNombre", equipoDAOHardCodeado.getById(ejercicio.getEquipoID()).getNombre());
            request.getRequestDispatcher("WEB-INF/jsp/editarEjercicio.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar los datos para editar ejercicio", e);
        }
    }

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
            Ejercicio ejercicio = ejercicioDAOHardCodeado.getById(ejercicioId);
            int parteDelCuerpoID = parteDelCuerpoDAOHardCodeado.getByName(musculoPrincipal).getParteDelCuerpoID();
            int equipoID = equipoDAOHardCodeado.getByName(equipoNombre).getEquipoID();

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                String filePath = "C:\\Users\\Francisco\\Desktop\\gym-web-project-java\\src\\main\\webapp\\assets\\img\\" + fileName;

                // Save the file to the server
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath());
                }

                // Correct the image orientation
                try {
                    correctImageOrientation(file);
                } catch (ImageProcessingException | MetadataException e) {
                    throw new RuntimeException(e);
                }

                ejercicio.setImagen(fileName);
            }

            ejercicio.setNombre(nombre);
            ejercicio.setMusculosQueTrabaja(musculosQueTrabaja);
            ejercicio.setPreparacion(preparacion);
            ejercicio.setConsejosClave(consejosClave);
            ejercicio.setDescripcion(descripcion);
            ejercicio.setEjecucion(ejecucion);
            ejercicio.setMusculoPrincipal(musculoPrincipal);
            ejercicio.setParteDelCuerpoID(parteDelCuerpoID);
            ejercicio.setEquipoID(equipoID);

            ejercicioDAOHardCodeado.update(ejercicio);
            response.sendRedirect("mostrarEjercicios?categoriaId=" + parteDelCuerpoID);
        } catch (Exception e) {
            throw new ServletException("Error al editar el ejercicio", e);
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void correctImageOrientation(File file) throws IOException, ImageProcessingException, MetadataException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        if (directory != null && directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
            int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            BufferedImage image = ImageIO.read(file);
            BufferedImage rotatedImage = rotateImage(image, orientation);
            ImageIO.write(rotatedImage, "jpg", file);
        }
    }

    private BufferedImage rotateImage(BufferedImage image, int orientation) {
        int width = image.getWidth();
        int height = image.getHeight();
        AffineTransform transform = new AffineTransform();
        switch (orientation) {
            case 1: // [ExifIFD0Directory.ORIENTATION_NORMAL]
                return image;
            case 6: // [ExifIFD0Directory.ORIENTATION_ROTATE_90]
                transform.translate(height, 0);
                transform.rotate(Math.toRadians(90));
                break;
            case 3: // [ExifIFD0Directory.ORIENTATION_ROTATE_180]
                transform.translate(width, height);
                transform.rotate(Math.toRadians(180));
                break;
            case 8: // [ExifIFD0Directory.ORIENTATION_ROTATE_270]
                transform.translate(0, width);
                transform.rotate(Math.toRadians(270));
                break;
            default:
                return image;
        }
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);
    }
}