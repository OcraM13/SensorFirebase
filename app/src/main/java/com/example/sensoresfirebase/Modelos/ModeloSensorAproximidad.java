package com.example.sensoresfirebase.Modelos;

public class ModeloSensorAproximidad {
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

    public ModeloSensorAproximidad(double distancia) {
        this.id = "0";
        this.distancia = distancia;
    }
}
