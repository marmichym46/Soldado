package com.example.soldado;

public class SpinnerCompania {
    private int id_compania;
    private String nombrecompania;

    public SpinnerCompania() {
    }

    public SpinnerCompania(int id_compania, String nombrecompania) {
        this.id_compania = id_compania;
        this.nombrecompania = nombrecompania;
    }

    public int getId_compania() {
        return id_compania;
    }

    public void setId_compania(int id_compania) {
        this.id_compania = id_compania;
    }

    public String getNombrecompania() {
        return nombrecompania;
    }

    public void setNombrecompania(String nombrecompania) {
        this.nombrecompania = nombrecompania;
    }

    public String toString() {
        return nombrecompania;
    }
}
