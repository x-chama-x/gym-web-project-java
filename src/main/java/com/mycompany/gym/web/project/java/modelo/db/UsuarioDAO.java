package com.mycompany.gym.web.project.java.modelo.db;

import com.mycompany.gym.web.project.java.modelo.RolUsuario;
import com.mycompany.gym.web.project.java.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// autenticar a los usuarios en la base de datos
public class UsuarioDAO {
    public Usuario autenticar(String nombre, String contrasena) {
        Usuario u = null;
        String query = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contrasena);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    u = rsRowToUsuario(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return u;
    }

    // Convertir un ResultSet a un objeto Usuario
    private Usuario rsRowToUsuario(ResultSet resultSet) {
        try {
            Usuario usuario = new Usuario();
            usuario.setUsuarioID(resultSet.getInt("usuarioID"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setCorreo(resultSet.getString("correo"));
            usuario.setContrasena(resultSet.getString("contraseña"));
            // como puse en la base de datos los roles como "final" y "administrador", los convierto a los valores del Enum
            usuario.setRol(resultSet.getString("rol").equals("final") ? RolUsuario.USUARIO_FINAL : RolUsuario.ADMINISTRADOR);
            return usuario;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
