package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

public class ParteDelCuerpoDAOHardCodeado implements DAO<ParteDelCuerpo, Integer> {

    private List<ParteDelCuerpo> categorias;

    public ParteDelCuerpoDAOHardCodeado() {
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


    // metodo que devuelve una lista de todas las categorias
    @Override
    public List<ParteDelCuerpo> getAll() throws Exception {
        return new ArrayList<>(categorias);
    }

    // metodo que devuelve una categoria por su id
    @Override
    public ParteDelCuerpo getById(Integer id) throws Exception {
        ParteDelCuerpo categoria = null;
        int i = 0;
        while (i < categorias.size() && categoria == null) {
            if (categorias.get(i).getParteDelCuerpoID() == id) {
                categoria = categorias.get(i);
            }
            i++;
        }
        if (categoria == null) {
            throw new IllegalArgumentException("Categoría no encontrada: " + id);
        }
        return categoria;
    }

    public ParteDelCuerpo getByName(String nombre) throws Exception {
        ParteDelCuerpo categoria = null;
        int i = 0;
        while (i < categorias.size() && categoria == null) {
            if (categorias.get(i).getNombre().equals(nombre)) {
                categoria = categorias.get(i);
            }
            i++;
        }
        if (categoria == null) {
            throw new IllegalArgumentException("Categoría no encontrada: " + nombre);
        }
        return categoria;
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
}
