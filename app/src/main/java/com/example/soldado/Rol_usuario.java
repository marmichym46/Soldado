package com.example.soldado;

public class Rol_usuario {
    private int id_rol;
    private String nombre_rol;

    public Rol_usuario() {
    }

    public Rol_usuario(int id_rol, String nombre_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
    }

    //GETTER

    public int getId_rol() {
        return id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    //SETTER

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    @Override
    public String toString() {
        return nombre_rol;
    }
}
