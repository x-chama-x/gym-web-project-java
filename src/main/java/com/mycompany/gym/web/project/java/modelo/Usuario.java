package com.mycompany.gym.web.project.java.modelo;

import java.io.Serializable;


public class Usuario implements Serializable {
    private int usuarioID;
    private String nombre;
    private String correo;
    private String contrasena;
    private RolUsuario rol;

    public Usuario() {
    }

    public Usuario(int usuarioID, String nombre, String correo, String contrasena, RolUsuario rol) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioID=" + usuarioID + ", nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + ", rol=" + rol + '}';
    }
}
