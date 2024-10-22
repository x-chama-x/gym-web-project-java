package com.mycompany.gym.web.project.java.modelo;

import java.util.ArrayList;
import java.util.List;

// clase auxiliar para almacenar los ejercicios con sus series
// no queria contaminar el javaBean (tabla intermedia) EntrenamientoHasEjercicio

public class EjercicioConSeries {
    private Ejercicio ejercicio;
    private List<EntrenamientoHasEjercicio> series;
    private int maxSeries;

    public EjercicioConSeries(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
        this.series = new ArrayList<>();
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<EntrenamientoHasEjercicio> getSeries() {
        return series;
    }

    public void agregarSerie(EntrenamientoHasEjercicio serie) {
        this.series.add(serie);
    }

    public int getMaxSeries() {
        return maxSeries;
    }


    public void setMaxSeries() {
        int max = 0;
        for (EntrenamientoHasEjercicio serie : series) {
            if (serie.getNumeroSerie() > max) {
                max = serie.getNumeroSerie();
            }
        }
        this.maxSeries = max;
    }

    @Override
    public String toString() {
        return "EjercicioConSeries{" +
                "ejercicio=" + ejercicio +
                '}';
    }
}