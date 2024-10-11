package com.mycompany.gym.web.project.java.modelo.db;

import com.mycompany.gym.web.project.java.modelo.DAO;
import com.mycompany.gym.web.project.java.modelo.ParteDelCuerpo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParteDelCuerpoDAO implements DAO<ParteDelCuerpo, Integer> {


    // metodo que devuelve una lista de todas las categorias
    @Override
    public List<ParteDelCuerpo> getAll() throws Exception {
        List<ParteDelCuerpo> categorias = new ArrayList<>();
        String query = "SELECT * FROM partedelcuerpo";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categorias.add(rsRowToParteDelCuerpo(resultSet));
            }
        } catch (SQLException ex) {
        throw new RuntimeException(ex);
        }
        return categorias;
    }

    // metodo que convierte una fila de la tabla partedelcuerpo en un objeto ParteDelCuerpo
    private ParteDelCuerpo rsRowToParteDelCuerpo(ResultSet resultSet) {
        try {
            ParteDelCuerpo parteDelCuerpo = new ParteDelCuerpo();
            parteDelCuerpo.setParteDelCuerpoID(resultSet.getInt("parteDelCuerpoID"));
            parteDelCuerpo.setNombre(resultSet.getString("nombre"));
            parteDelCuerpo.setImagen(resultSet.getString("imagen"));
            return parteDelCuerpo;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ParteDelCuerpo getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // metodos no implementados
    @Override
    public void add(ParteDelCuerpo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ParteDelCuerpo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
