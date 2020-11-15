package com.example.soldado;

public class SpinnerCargo {
    private int id_cargo;
    private String nombrecargo;

    public SpinnerCargo() {
    }

    public SpinnerCargo(int id_cargo, String nombrecargo) {
        this.id_cargo = id_cargo;
        this.nombrecargo = nombrecargo;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public String toString() {
        return nombrecargo;
    }
}
