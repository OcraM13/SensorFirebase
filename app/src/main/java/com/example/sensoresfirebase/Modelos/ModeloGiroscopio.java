package com.example.sensoresfirebase.Modelos;

public class ModeloGiroscopio {
    private double x;
    private double y;
    private double z;

    public ModeloGiroscopio(double x, double y, double z) {
        this.id = "0";
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
