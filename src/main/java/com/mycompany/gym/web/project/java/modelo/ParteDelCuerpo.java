package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class ParteDelCuerpo implements Serializable {
    private int parteDelCuerpoID;
    private String nombre;
    private String imagen;

    public ParteDelCuerpo() {
    }

    public int getParteDelCuerpoID() {
        return parteDelCuerpoID;
    }

    public ParteDelCuerpo(int parteDelCuerpoID, String nombre, String imagen) {
        this.parteDelCuerpoID = parteDelCuerpoID;
        this.nombre = nombre;
        this.imagen = imagen;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
