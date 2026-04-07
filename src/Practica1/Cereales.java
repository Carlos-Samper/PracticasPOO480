package Practica1;

import java.util.Date;


public class Cereales extends Producto implements Alimento {

    private TipoCereal tipoCereal;
    private Date caducidad;

    public Cereales(String marca, float precio, TipoCereal tipoCereal) {
        super(marca, precio);
        this.tipoCereal = tipoCereal;
    }

    public TipoCereal getTipoCereal() {
        return tipoCereal;
    }

    public void setTipoCereal(TipoCereal tipoCereal) {
        this.tipoCereal = tipoCereal;
    }

    @Override
    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public Date getCaducidad() {
        return caducidad;
    }

    @Override
    public int getCalorias() {
        return tipoCereal.getCalorias();
    }

    @Override
    public String toString() {
        return "Cereales{marca=" + getMarca() +
                ", precio=" + getPrecio() +
                ", tipo=" + tipoCereal + "}";
    }
}
