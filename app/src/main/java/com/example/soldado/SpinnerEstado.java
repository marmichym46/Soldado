package com.example.soldado;

public class SpinnerEstado {
    private int id_estado;
    private String nombreestado;

    public SpinnerEstado() {
    }

    public SpinnerEstado(int id_estado, String nombreestado) {
        this.id_estado = id_estado;
        this.nombreestado = nombreestado;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombreestado() {
        return nombreestado;
    }

    public void setNombreestado(String nombreestado) {
        this.nombreestado = nombreestado;
    }

    public String toString() {
        return nombreestado;
    }
}
