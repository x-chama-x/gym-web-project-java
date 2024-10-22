package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntrenamientoHasEjercicio implements Serializable {
    private int ejercicioEntrenamientoID;
    private int entrenamientoID;
    private int ejercicioID;
    private int numeroSerie;
    private double peso;
    private int repeticiones;

    public EntrenamientoHasEjercicio() {
    }

    public EntrenamientoHasEjercicio(int ejercicioEntrenamientoID, int entrenamientoID, int ejercicioID, int numeroSerie, double peso, int repeticiones) {
        this.ejercicioEntrenamientoID = ejercicioEntrenamientoID;
        this.entrenamientoID = entrenamientoID;
        this.ejercicioID = ejercicioID;
        this.numeroSerie = numeroSerie;
        this.peso = peso;
        this.repeticiones = repeticiones;
    }

    // Filtrar los datos de la tabla intermedia por el ID del entrenamiento
    public static List<EntrenamientoHasEjercicio> filtrarPorEntrenamientoID(List<EntrenamientoHasEjercicio> ejercicios, int entrenamientoID) {
        List<EntrenamientoHasEjercicio> ejerciciosFiltrados = new ArrayList<>();
        for (EntrenamientoHasEjercicio e : ejercicios) {
            if (e.getEntrenamientoID() == entrenamientoID) {
                ejerciciosFiltrados.add(e);
            }
        }
        return ejerciciosFiltrados;
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

    @Override
    public String toString() {
        return "EntrenamientoHasEjercicio{" + "ejercicioEntrenamientoID=" + ejercicioEntrenamientoID + ", entrenamientoID=" + entrenamientoID + ", ejercicioID=" + ejercicioID + ", numeroSerie=" + numeroSerie + ", peso=" + peso + ", repeticiones=" + repeticiones + '}';
    }
}
