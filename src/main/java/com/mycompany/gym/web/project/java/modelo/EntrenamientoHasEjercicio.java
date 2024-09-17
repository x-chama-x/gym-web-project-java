package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class EntrenamientoHasEjercicio implements Serializable {
    private int ejercicioEntrenamientoID;
    private int entrenamientoID;
    private int ejercicioID;
    private int numeroSerie;
    private double peso;
    private int repeticiones;

    public EntrenamientoHasEjercicio() {
    }

    public int getEjercicioEntrenamientoID() {
        return ejercicioEntrenamientoID;
    }

    public void setEjercicioEntrenamientoID(int ejercicioEntrenamientoID) {
        this.ejercicioEntrenamientoID = ejercicioEntrenamientoID;
    }

    public int getEntrenamientoID() {
        return entrenamientoID;
    }

    public void setEntrenamientoID(int entrenamientoID) {
        this.entrenamientoID = entrenamientoID;
    }

    public int getEjercicioID() {
        return ejercicioID;
    }

    public void setEjercicioID(int ejercicioID) {
        this.ejercicioID = ejercicioID;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
}
