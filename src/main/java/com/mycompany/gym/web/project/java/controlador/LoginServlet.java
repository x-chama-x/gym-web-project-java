package com.mycompany.gym.web.project.java.controlador;
import com.mycompany.gym.web.project.java.modelo.Usuario;
import com.mycompany.gym.web.project.java.modelo.UsuarioDAOHardCodeado;
import com.mycompany.gym.web.project.java.modelo.db.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginServlet extends HttpServlet {

    // mandar siempre a la pagina principal (index.jsp)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }

    // autenticar al usuario que se loguea y enviarlo a la pagina principal
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // creo un objeto de la clase UsuarioDAO para autenticar al usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // obtengo los datos del usuario del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // autentico al usuario
        Usuario usuario = usuarioDAO.autenticar(username, password);

        if (usuario != null) { // si el usuario es autenticado correctamente lo guardo en la sesión
            HttpSession session = request.getSession(); // obtengo la sesión
            session.setAttribute("userLogueado", usuario);
            session.setAttribute("rolUsuario", usuario.getRol().name()); // Guardo el rol del usuario autenticado para mostrar o no ciertas opciones en la vista
            session.setMaxInactiveInterval(-1); // La sesión no expira
            response.sendRedirect(request.getContextPath() + "/perfil"); // Redirijo al perfil del usuario
        } else {
            request.setAttribute("errorMessage", "datos incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
