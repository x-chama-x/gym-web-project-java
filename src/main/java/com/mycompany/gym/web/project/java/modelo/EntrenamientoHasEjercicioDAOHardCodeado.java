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

    // se cargan los datos de la tabla intermedia EntrenamientoHasEjercicio (series, peso, repeticiones, etc)
    private void cargarEjerciciosDeEntrenamiento() {
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(1, 1, 1, 1, 10.0, 8));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(2, 1, 1, 2, 15.0, 12));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(3, 1, 1, 3, 15.0, 12));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(4, 2, 4, 1, 100.0, 100));
        entrenamientosHasEjercios.add(new EntrenamientoHasEjercicio(5, 2, 4, 2, 100.0, 100));
    }

    @Override
    public List<EntrenamientoHasEjercicio> getAll() throws Exception {
        return new ArrayList<>(entrenamientosHasEjercios);
    }

    // metodos no implementados


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
    public EntrenamientoHasEjercicio getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
