package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    private int ejercicioID;
    private int usuarioID;
    private int equipoID;
    private int parteDelCuerpoID;
    private String nombre;
    private String imagen;
    private String musculosQueTrabaja;
    private String preparacion;
    private String consejosClave;
    private String descripcion;
    private String ejecucion;
    private String musculoPrincipal;
    private CargadoPor cargadoPor;

    public Ejercicio() {
    }

    public int getEjercicioID() {
        return ejercicioID;
    }

    public void setEjercicioID(int ejercicioID) {
        this.ejercicioID = ejercicioID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(int equipoID) {
        this.equipoID = equipoID;
    }

    public int getParteDelCuerpoID() {
        return parteDelCuerpoID;
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

    public String getMusculosQueTrabaja() {
        return musculosQueTrabaja;
    }

    public void setMusculosQueTrabaja(String musculosQueTrabaja) {
        this.musculosQueTrabaja = musculosQueTrabaja;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getConsejosClave() {
        return consejosClave;
    }

    public void setConsejosClave(String consejosClave) {
        this.consejosClave = consejosClave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(String ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getMusculoPrincipal() {
        return musculoPrincipal;
    }

    public void setMusculoPrincipal(String musculoPrincipal) {
        this.musculoPrincipal = musculoPrincipal;
    }

    public CargadoPor getCargadoPor() {
        return cargadoPor;
    }

    public void setCargadoPor(CargadoPor cargadoPor) {
        this.cargadoPor = cargadoPor;
    }
}
