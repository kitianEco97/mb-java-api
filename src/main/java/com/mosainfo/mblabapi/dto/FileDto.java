package com.mosainfo.mblabapi.dto;

import javax.validation.constraints.NotBlank;

public class FileDto {

    // HEADERS
    @NotBlank
    private String proyecto;
    @NotBlank
    private String cliente;
    @NotBlank
    private String tipohormigon;
    @NotBlank
    private int numeromuestra;
    @NotBlank
    private float volumen;
    @NotBlank
    private int nhp;

    // DISEÑO
    /*private String marca;
    private float abs;
    private float lote;
    private float pesoseco;
    private float pesosecobatch;
    private float humedad;
    private float agua;
    private float pesohumedo;*/

    public FileDto() {
    }

    public FileDto(String proyecto, String cliente, String tipohormigon, int numeromuestra, float volumen, int numerohormigonprueba, String marca, float abs, float lote, float pesoseco, float pesosecobatch, float humedad, float agua, float pesohumedo) {
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.tipohormigon = tipohormigon;
        this.numeromuestra = numeromuestra;
        this.volumen = volumen;
        this.nhp = numerohormigonprueba;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipohormigon() {
        return tipohormigon;
    }

    public void setTipohormigon(String tipohormigon) {
        this.tipohormigon = tipohormigon;
    }

    public int getNumeromuestra() {
        return numeromuestra;
    }

    public void setNumeromuestra(int numeromuestra) {
        this.numeromuestra = numeromuestra;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public int getNhp() {
        return nhp;
    }

    public void setNhp(int nhp) {
        this.nhp = nhp;
    }

}
