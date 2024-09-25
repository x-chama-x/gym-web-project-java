package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class ParteDelCuerpoDAO implements DAO<ParteDelCuerpo, Integer> {

    private List<ParteDelCuerpo> categorias;

    public ParteDelCuerpoDAO() {
        this.categorias = new ArrayList<>();
        cargarCategorias();
    }

    private void cargarCategorias() {
        categorias.add(new ParteDelCuerpo(1, "Pecho", "pecho.jpg"));
        categorias.add(new ParteDelCuerpo(2, "Espalda", "espalda.jpg"));
        categorias.add(new ParteDelCuerpo(3, "Piernas", "piernas.jpg"));
        categorias.add(new ParteDelCuerpo(4, "Hombros", "hombros.jpg"));
        categorias.add(new ParteDelCuerpo(5, "Brazos", "brazos.jpg"));
        categorias.add(new ParteDelCuerpo(6, "Abdominales", "abdominales.jpg"));
    }

    @Override
    public List<ParteDelCuerpo> getAll() throws Exception {
        return new ArrayList<>(categorias);
    }

    // metodos no implementados
    @Override
    public void add(ParteDelCuerpo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(ParteDelCuerpo entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParteDelCuerpo getById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
