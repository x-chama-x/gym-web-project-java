package com.mycompany.gym.web.project.java.modelo.db;

import com.mycompany.gym.web.project.java.modelo.CargadoPor;
import com.mycompany.gym.web.project.java.modelo.DAO;
import com.mycompany.gym.web.project.java.modelo.Ejercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjercicioDAO implements DAO<Ejercicio,Integer> {

    @Override
    public void add(Ejercicio entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Ejercicio entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Ejercicio> getAll() throws Exception {
        List <Ejercicio> ejercicios = new ArrayList<>();
        String query = "SELECT * FROM ejercicio";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ejercicios.add(rsRowToEjercicio(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return ejercicios;
    }

    private Ejercicio rsRowToEjercicio(ResultSet resultSet) {
        try {
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setEjercicioID(resultSet.getInt("ejercicioID"));
            ejercicio.setNombre(resultSet.getString("nombre"));
            ejercicio.setImagen(resultSet.getString("imagen"));
            ejercicio.setDescripcion(resultSet.getString("descripcion"));
            ejercicio.setParteDelCuerpoID(resultSet.getInt("parteDelCuerpoID"));
            ejercicio.setMusculosQueTrabaja(resultSet.getString("musculosQueTrabaja"));
            ejercicio.setEquipoID(resultSet.getInt("equipoID"));
            ejercicio.setDescripcion(resultSet.getString("descripcion"));
            ejercicio.setPreparacion(resultSet.getString("preparacion"));
            ejercicio.setEjecucion(resultSet.getString("ejecucion"));
            ejercicio.setConsejosClave(resultSet.getString("consejosClave"));
            ejercicio.setCargadoPor(CargadoPor.valueOf(resultSet.getString("cargadoPor").toUpperCase())); // Convertir el String a Enum
            return ejercicio;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Ejercicio getById(Integer id) throws Exception {
        String query = "SELECT * FROM ejercicio WHERE ejercicioID = ?";
        Ejercicio ejercicio = null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ejercicio = rsRowToEjercicio(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return ejercicio;
    }

    // metodo que devuelve una lista de ejercicios por el ID de la parte del cuerpo
    public ArrayList<Ejercicio> getByParteDelCuerpoID(int categoriaId) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        String query = "SELECT * FROM ejercicio WHERE parteDelCuerpoID = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, categoriaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ejercicios.add(rsRowToEjercicio(resultSet));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return ejercicios;
    }
}
