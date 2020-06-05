package com.example.sensoresfirebase.Modelos;

public class ModeloLuz {
    private String id;
    double medida;

    public String getId() {
        return id;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }

    public ModeloLuz(double medida) {
        this.medida = medida;
    }
}
