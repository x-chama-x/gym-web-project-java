package com.mycompany.gym.web.project.java.modelo.db;

import com.mycompany.gym.web.project.java.modelo.RolUsuario;
import com.mycompany.gym.web.project.java.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Metodo que autentica un usuario por su nombre y contraseña
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

    // convierte una fila de la tabla usuario en un objeto Usuario
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

    private int obtenerMaxUsuarioID() {
        String query = "SELECT MAX(usuarioID) AS maxID FROM usuario";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("maxID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0; // Si no hay usuarios, retorna 0
    }

    // Metodo que registra un usuario con rol de usuario final
    public void registrar(String nombre, String contrasena, String correo) {
        int nuevoUsuarioID = obtenerMaxUsuarioID() + 1;
        String query = "INSERT INTO usuario (usuarioID, nombre, contraseña, correo, rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, nuevoUsuarioID);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, contrasena);
            preparedStatement.setString(4, correo);
            preparedStatement.setString(5, "final");
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
