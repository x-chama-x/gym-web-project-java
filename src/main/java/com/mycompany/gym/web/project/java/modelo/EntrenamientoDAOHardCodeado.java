package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class EntrenamientoDAOHardCodeado implements DAO<Entrenamiento,Integer> {
    private ArrayList<Entrenamiento> entrenamientos;
    private static EntrenamientoDAOHardCodeado instance; // Singleton para que solo haya una instancia de la clase

    public EntrenamientoDAOHardCodeado() {
        this.entrenamientos = new ArrayList<>();
        cargarEntrenamientos();
    }

    public static synchronized EntrenamientoDAOHardCodeado getInstance() {
        if (instance == null) {
            instance = new EntrenamientoDAOHardCodeado();
        }
        return instance;
    }

    // Datos Hardcodedeados de los entrenamientos
    private void cargarEntrenamientos() {
        entrenamientos.add(new Entrenamiento(1, 6, "Dia 1", 1));
        entrenamientos.add(new Entrenamiento(2, 5, "Dia 2", 1));
    }

    public List<Entrenamiento> getByUserId(int userId) throws Exception {
        List<Entrenamiento> entrenamientosUsuario = new ArrayList<>();
        for (Entrenamiento entrenamiento : entrenamientos) {
            if (entrenamiento.getUsuarioID() == userId) {
                entrenamientosUsuario.add(entrenamiento);
            }
        }
        return entrenamientosUsuario;
    }

    @Override
    public void add(Entrenamiento entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Entrenamiento entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Entrenamiento> getAll() throws Exception {
        return new ArrayList<>(entrenamientos);
    }

    @Override
    public Entrenamiento getById(Integer id) throws Exception {
        Entrenamiento entrenamiento = null;
        int i = 0;
        while (i < entrenamientos.size() && entrenamiento == null) {
            if (entrenamientos.get(i).getEntrenamientoID() == id) {
                entrenamiento = entrenamientos.get(i);
            }
            i++;
        }
        return entrenamiento;
    }
}
