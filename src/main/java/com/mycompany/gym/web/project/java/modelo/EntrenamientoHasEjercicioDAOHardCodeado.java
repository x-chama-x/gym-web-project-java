package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class EntrenamientoHasEjercicioDAOHardCodeado implements DAO<EntrenamientoHasEjercicio,Integer> {
    private ArrayList<EntrenamientoHasEjercicio> entrenamientosHasEjercios;
    private static EntrenamientoHasEjercicioDAOHardCodeado instance; // Singleton para que solo haya una instancia de la clase

    public EntrenamientoHasEjercicioDAOHardCodeado() {
        this.entrenamientosHasEjercios = new ArrayList<>();
        cargarEjerciciosDeEntrenamiento();
    }

    public static synchronized EntrenamientoHasEjercicioDAOHardCodeado getInstance() {
        if (instance == null) {
            instance = new EntrenamientoHasEjercicioDAOHardCodeado();
        }
        return instance;
    }

    private void cargarEjerciciosDeEntrenamiento() {
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(1, 1, 1, 1, 10.0, 10));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(2, 1, 2, 1, 10.0, 10));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(3, 2, 3, 2, 10.0, 10));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(4, 2, 4, 2, 10.0, 10));
    }


    @Override
    public void add(EntrenamientoHasEjercicio entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(EntrenamientoHasEjercicio entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EntrenamientoHasEjercicio> getAll() throws Exception {
        return new ArrayList<>(entrenamientosHasEjercios);
    }

    @Override
    public EntrenamientoHasEjercicio getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
