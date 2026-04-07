package Practica1.Clases;

import Practica1.Intefaces.Descuento;
import Practica1.Intefaces.Liquido;

public class Detergente extends Producto implements Liquido, Descuento {

    private float volumen;
    private String tipoEnvase;
    private float descuento;

    public Detergente(String marca, float precio) {
        super(marca, precio);
        this.descuento = 0f;
    }

    @Override
    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    @Override
    public float getVolumen() {
        return volumen;
    }

    @Override
    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }

    @Override
    public String getTipoEnvase() {
        return tipoEnvase;
    }

    @Override
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public float getDescuento() {
        return descuento;
    }

    @Override
    public float getPrecioDescuento() {
        return getPrecio() - (getPrecio() * descuento / 100);
    }

    @Override
    public String toString() {
        return "Detergente{marca=" + getMarca() +
                ", precio=" + getPrecio() +
                ", volumen=" + volumen +
                ", envase=" + tipoEnvase +
                ", descuento=" + descuento + "}";
    }
}
