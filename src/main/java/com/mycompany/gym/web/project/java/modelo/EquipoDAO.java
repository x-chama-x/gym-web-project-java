package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class EquipoDAO implements DAO<Equipo, Integer> {
    private List<Equipo> equipos;

    public EquipoDAO() {
        this.equipos = new ArrayList<>();
        cargarEquipos();
    }

    private void cargarEquipos() {
        equipos.add(new Equipo(1, "Banco plano", "banco_plano.jpg"));
        equipos.add(new Equipo(2, "Mancuernas", "mancuernas.jpg"));
        equipos.add(new Equipo(3, "Peso corporal", "peso_corporal.jpg"));
        equipos.add(new Equipo(4, "Barra de pesas", "barra.jpg"));
        equipos.add(new Equipo(5, "Máquina de poleas", "poleas.jpg"));
    }

    @Override
    public List<Equipo> getAll() throws Exception {
        return new ArrayList<>(equipos);
    }

    @Override
    public Equipo getById(Integer id) throws Exception {
        Equipo equipo = null;
        int i = 0;
        while (i < equipos.size() && equipo == null) {
            if (equipos.get(i).getEquipoID() == id) {
                equipo = equipos.get(i);
            }
            i++;
        }
        if (equipo == null) {
            throw new IllegalArgumentException("Equipo no encontrado: " + id);
        }
        return equipo;
    }

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