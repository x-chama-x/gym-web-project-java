package com.mycompany.gym.web.project.java.modelo;


// este metodo es para autenticar a los usuarios, es hardcodeado.
public class UsuarioDAOHardCodeado {
    public Usuario autenticar (String nombre, String contrasena) {
        Usuario u = null;
        if (contrasena.equals("123456")) {
            switch (nombre) {
                case "francisco":
                    u = new Usuario(1, nombre,"franciscofchiminelli@hotmail.com", contrasena, RolUsuario.ADMINISTRADOR);
                    break;
                case "tadeo":
                    u = new Usuario(2, nombre,"tadeoinventado@hotmail.com", contrasena, RolUsuario.USUARIO_FINAL);
                    break;
                        }
        } return u;
    }
}
