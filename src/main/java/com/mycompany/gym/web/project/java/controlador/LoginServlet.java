package com.mycompany.gym.web.project.java.controlador;
import com.mycompany.gym.web.project.java.modelo.Usuario;
import com.mycompany.gym.web.project.java.modelo.UsuarioDAOHardCodeado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private UsuarioDAOHardCodeado usuarioDAO = new UsuarioDAOHardCodeado();


    // este metodo es para autenticar a los usuarios
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // obtengo los datos del usuario del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // autentico al usuario
        Usuario usuario = usuarioDAO.autenticar(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogueado", usuario);
            session.setAttribute("rolUsuario", usuario.getRol().name()); // Guardo el rol del usuario autenticado para mostrar o no ciertas opciones en la vista
            // necesito usar un RequestDispatcher en lugar de sendRedirect porque los archivos dentro de WEB-INF no son accesibles directamente desde el navegador
            request.getRequestDispatcher("WEB-INF/jsp/principal.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "datos incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // utilizo un metodo doPost solamente ya que tengo el formulario en el index.jsp
    // y no necesito un doGet para mostrar la pagina de login
}
