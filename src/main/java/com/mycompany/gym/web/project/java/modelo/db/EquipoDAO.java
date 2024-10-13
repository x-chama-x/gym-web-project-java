package com.mycompany.gym.web.project.java.modelo.db;

import com.mycompany.gym.web.project.java.modelo.DAO;
import com.mycompany.gym.web.project.java.modelo.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO implements DAO<Equipo, Integer> {

    // metodo que devuelve una lista de todos los equipos
    @Override
    public List<Equipo> getAll() throws Exception {
        List <Equipo> equipos = new ArrayList<>();
        String query = "SELECT * FROM equipo";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                equipos.add(rsRowToEquipo(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return equipos;
    }

    // metodo que convierte una fila de la tabla equipo en un objeto Equipo
    private Equipo rsRowToEquipo(ResultSet resultSet) {
        try {
            Equipo equipo = new Equipo();
            equipo.setEquipoID(resultSet.getInt("equipoID"));
            equipo.setNombre(resultSet.getString("nombre"));
            equipo.setImagen(resultSet.getString("imagen"));
            return equipo;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // metodo que devuelve una categoria por su ID
    @Override
    public Equipo getById(Integer id) throws Exception {
        Equipo equipo = null;
        String query = "SELECT * FROM equipo WHERE equipoID = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    equipo = rsRowToEquipo(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return equipo;
    }
    // metodo que devuelve una categoria por su nombre
    public Equipo getByName(String nombre) throws Exception {
        Equipo equipo = null;
        String query = "SELECT * FROM equipo WHERE nombre = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    equipo = rsRowToEquipo(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return equipo;
    }

    // metodos no implementados
    @Override
    public void add(Equipo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Equipo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
