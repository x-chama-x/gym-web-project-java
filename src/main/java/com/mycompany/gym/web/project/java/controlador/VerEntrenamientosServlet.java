package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Entrenamiento;
import com.mycompany.gym.web.project.java.modelo.EntrenamientoDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class VerEntrenamientosServlet extends HttpServlet {
    private EntrenamientoDAOHardCodeado entrenamientoDAO;


    // inicializar el servlet y cargar los entrenamientos
    @Override
    public void init() throws ServletException {
        entrenamientoDAO = new EntrenamientoDAOHardCodeado();
    }

    // muestra los entrenamientos en la pagina verEntrenamientos.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Obtener el ID del usuario de la sesión
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("userLogueado");
            int userId = usuario.getUsuarioID();

            ArrayList<Entrenamiento> entrenamientos = new ArrayList<>(entrenamientoDAO.getByUserId(userId)); // obtengo los entrenamientos del usuario
            request.setAttribute("entrenamientos", entrenamientos); // establezco los entrenamientos como atributo de la request (solicitud)
            request.getRequestDispatcher("WEB-INF/jsp/verEntrenamientos.jsp").forward(request, response); // se reenvia la solicitud a la pagina verEntrenamientos.jsp
        } catch (Exception e) {
            throw new ServletException("Error al cargar los entrenamientos", e); // si ocurre un error se lanza una excepcion
        }
    }

}
