package com.example.soldado;

public class LisSoldado {
    private int id_soldado;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String cargo;
    private String telefono;
    private String fecha;
    private String grado;
    private String batallon;
    private String compania;
    private String estado;

    public LisSoldado() {
    }

    public LisSoldado(int id_soldado, String cedula, String nombres, String apellidos, String cargo, String telefono, String fecha, String grado, String batallon, String compania, String estado) {
        this.id_soldado = id_soldado;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.telefono = telefono;
        this.fecha = fecha;
        this.grado = grado;
        this.batallon = batallon;
        this.compania = compania;
        this.estado = estado;
    }

    public int getId_soldado() {
        return id_soldado;
    }

    public void setId_soldado(int id_soldado) {
        this.id_soldado = id_soldado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getBatallon() {
        return batallon;
    }

    public void setBatallon(String batallon) {
        this.batallon = batallon;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
