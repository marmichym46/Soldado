package com.example.soldado;

public class SpinnerBatallon {
    private int id_batallon;
    private String nombrebatallon;

    public SpinnerBatallon() {
    }

    public SpinnerBatallon(int id_batallon, String nombrebatallon) {
        this.id_batallon = id_batallon;
        this.nombrebatallon = nombrebatallon;
    }

    public int getId_batallon() {
        return id_batallon;
    }

    public void setId_batallon(int id_batallon) {
        this.id_batallon = id_batallon;
    }

    public String getNombrebatallon() {
        return nombrebatallon;
    }

    public void setNombrebatallon(String nombrebatallon) {
        this.nombrebatallon = nombrebatallon;
    }

    public String toString() {
        return nombrebatallon;
    }
}
