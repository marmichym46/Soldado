package com.example.soldado;

public class SpinnerGrado {
    private int id_grado;
    private String nombregrado;

    public SpinnerGrado() {
    }

    public SpinnerGrado(int id_grado, String nombregrado) {
        this.id_grado = id_grado;
        this.nombregrado = nombregrado;
    }

    public int getId_grado() {
        return id_grado;
    }

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public String getNombregrado() {
        return nombregrado;
    }

    public void setNombregrado(String nombregrado) {
        this.nombregrado = nombregrado;
    }

    public String toString() {
        return nombregrado;
    }
}
