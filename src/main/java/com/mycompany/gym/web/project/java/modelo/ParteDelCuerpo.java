package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class ParteDelCuerpo implements Serializable {
    private int parteDelCuerpoID;
    private String nombre;

    public ParteDelCuerpo() {
    }

    public int getParteDelCuerpoID() {
        return parteDelCuerpoID;
    }

    public ParteDelCuerpo(int parteDelCuerpoID, String nombre) {
        this.parteDelCuerpoID = parteDelCuerpoID;
        this.nombre = nombre;
    }

    public void setParteDelCuerpoID(int parteDelCuerpoID) {
        this.parteDelCuerpoID = parteDelCuerpoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
