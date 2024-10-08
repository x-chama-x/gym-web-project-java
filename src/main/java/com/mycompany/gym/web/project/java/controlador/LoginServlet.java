package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.RolUsuario;
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.autenticar(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogueado", usuario);
            session.setAttribute("rolUsuario", usuario.getRol().name()); // Guardar el rol del usuario autenticado
            response.sendRedirect("principal.jsp");
        } else {
            request.setAttribute("errorMessage", "datos incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // utilizo un metodo doPost solamente ya que tengo el formulario en el index.jsp
    // y no necesito un doGet para mostrar la pagina de login
}
