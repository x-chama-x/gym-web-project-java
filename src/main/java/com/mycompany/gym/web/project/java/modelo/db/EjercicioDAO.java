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

    // Metodo para agregar un ejercicio a la base de datos
    @Override
    public void add(Ejercicio entidad) throws Exception {
        entidad.setEjercicioID(getNextId()); // Obtener el próximo ID incremental
        String query = "INSERT INTO ejercicio (ejercicioID, nombre, imagen, descripcion, parteDelCuerpoID, musculosQueTrabaja, equipoID, preparacion, ejecucion, consejosClave, cargadoPor, usuario_usuarioID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, entidad.getEjercicioID());
            preparedStatement.setString(2, entidad.getNombre());
            preparedStatement.setString(3, entidad.getImagen());
            preparedStatement.setString(4, entidad.getDescripcion());
            preparedStatement.setInt(5, entidad.getParteDelCuerpoID());
            preparedStatement.setString(6, entidad.getMusculosQueTrabaja());
            preparedStatement.setInt(7, entidad.getEquipoID());
            preparedStatement.setString(8, entidad.getPreparacion());
            preparedStatement.setString(9, entidad.getEjecucion());
            preparedStatement.setString(10, entidad.getConsejosClave());
            preparedStatement.setString(11, entidad.getCargadoPor().name()); // Convertir el Enum a String
            preparedStatement.setInt(12, entidad.getUsuarioID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Metodo para obtener el próximo ID incremental
    private int getNextId() throws SQLException {
        String query = "SELECT MAX(ejercicioID) AS maxId FROM ejercicio";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("maxId") + 1;
            } else {
                return 1; // Si no hay registros, el primer ID será 1
            }
        }
    }

    // Metodo para actualizar un ejercicio en la base de datos
    @Override
    public void update(Ejercicio entidad) throws Exception {
        String query = "UPDATE ejercicio SET nombre = ?, imagen = ?, parteDelCuerpoID = ?, musculosQueTrabaja = ?, equipoID = ?, descripcion = ?, preparacion = ?, ejecucion = ?, consejosClave = ?, cargadoPor = ? WHERE ejercicioID = ? ";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, entidad.getNombre());
            preparedStatement.setString(2, entidad.getImagen());
            preparedStatement.setInt(3, entidad.getParteDelCuerpoID());
            preparedStatement.setString(4, entidad.getMusculosQueTrabaja());
            preparedStatement.setInt(5, entidad.getEquipoID());
            preparedStatement.setString(6, entidad.getDescripcion());
            preparedStatement.setString(7, entidad.getPreparacion());
            preparedStatement.setString(8, entidad.getEjecucion());
            preparedStatement.setString(9, entidad.getConsejosClave());
            preparedStatement.setString(10, entidad.getCargadoPor().name()); // Convertir el Enum a String
            preparedStatement.setInt(11, entidad.getEjercicioID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Metodo para eliminar un ejercicio de la base de datos
    @Override
    public void delete(Integer id) throws Exception {
        String query = "DELETE FROM ejercicio WHERE ejercicioID = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Metodo para obtener todos los ejercicios de la base de datos
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

    // Metodo que convierte una fila de la tabla ejercicio en un objeto Ejercicio
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

    // Metodo para obtener un ejercicio por su ID
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
