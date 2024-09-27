package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class Equipo implements Serializable {
    private int equipoID;
    private String nombre;
    private String imagen;

    public Equipo() {
    }

    public Equipo(int equipoID, String nombre, String imagen) {
        this.equipoID = equipoID;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(int equipoID) {
        this.equipoID = equipoID;
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
