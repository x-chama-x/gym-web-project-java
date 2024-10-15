package com.mycompany.gym.web.project.java.controlador;

import com.mycompany.gym.web.project.java.modelo.Entrenamiento;
import com.mycompany.gym.web.project.java.modelo.EntrenamientoDAOHardCodeado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class verEntrenamientosServlet extends HttpServlet {
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
            ArrayList<Entrenamiento> entrenamientos = new ArrayList<>(entrenamientoDAO.getAll()); // obtengo los entrenamientos cargados en el DAO
            imprimirEntrenamientos();
            request.setAttribute("entrenamientos", entrenamientos); // establezco los entrenamientos como atributo de la request (solicitud)
            request.getRequestDispatcher("WEB-INF/jsp/verEntrenamientos.jsp").forward(request, response); // se reenvia la solicitud a la pagina verEntrenamientos.jsp
        } catch (Exception e) {
            throw new ServletException("Error al cargar los entrenamientos", e); // si ocurre un error se lanza una excepcion
        }
    }

    // imprimir lista de entrenamientos
    public void imprimirEntrenamientos() {
        try {
            ArrayList<Entrenamiento> entrenamientos = new ArrayList<>(entrenamientoDAO.getAll());
            for (Entrenamiento entrenamiento : entrenamientos) {
                System.out.println(entrenamiento);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los entrenamientos");
        }
    }
}
