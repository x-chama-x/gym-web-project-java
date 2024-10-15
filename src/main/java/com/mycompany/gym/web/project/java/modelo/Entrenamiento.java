package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class Entrenamiento implements Serializable {
    private int entrenamientoID;
    private int cantidadEjercicios;
    private String nombre;
    private int usuarioID;

    public Entrenamiento() {
    }

    public Entrenamiento(int entrenamientoID, int cantidadEjercicios, String nombre, int usuarioID) {
        this.entrenamientoID = entrenamientoID;
        this.cantidadEjercicios = cantidadEjercicios;
        this.nombre = nombre;
        this.usuarioID = usuarioID;
    }

    public int getEntrenamientoID() {
        return entrenamientoID;
    }

    public void setEntrenamientoID(int entrenamientoID) {
        this.entrenamientoID = entrenamientoID;
    }

    public int getCantidadEjercicios() {
        return cantidadEjercicios;
    }

    public void setCantidadEjercicios(int cantidadEjercicios) {
        this.cantidadEjercicios = cantidadEjercicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "entrenamientoID=" + entrenamientoID +
                ", cantidadEjercicios=" + cantidadEjercicios +
                ", nombre='" + nombre + '\'' +
                ", usuarioID=" + usuarioID +
                '}';
    }
}
