package com.example.sensoresfirebase.Modelos;

public class ModeloLuz {
    private String id;
    double distancia;

    public String getId() {
        return id;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public ModeloLuz(double distancia) {
        this.distancia = distancia;
    }
}
