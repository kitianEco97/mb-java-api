package com.mosainfo.mblabapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class File {

    // HEADERS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String proyecto;
    private String cliente;
    private String tipohormigon;
    private int numeromuestra;
    private float volumen;
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



    public File(String proyecto, String cliente, String tipohormigon, int numeromuestra, float volumen, int nhp) {
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.tipohormigon = tipohormigon;
        this.numeromuestra = numeromuestra;
        this.volumen = volumen;
        this.nhp = nhp;
    }

    public File() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
