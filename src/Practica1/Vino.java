package Practica1;

import java.util.Date;

public class Vino extends Producto implements Liquido, Alimento, Descuento {

    private static final int FACTOR_CALORIAS_ALCOHOL = 10;

    private TipoVino tipoVino;
    private float volumen;
    private String tipoEnvase;
    private Date caducidad;
    private float descuento;

    public Vino(String marca, TipoVino tipoVino, float precio) {
        super(marca, precio);
        this.tipoVino  = tipoVino;
        this.descuento = 0f;
    }

    public TipoVino getTipoVino() {
        return tipoVino;
    }

    public void setTipoVino(TipoVino tipoVino) {
        this.tipoVino = tipoVino;
    }

    public float getGradosAlcohol() {
        return tipoVino.getGradosAlcohol();
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
    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public Date getCaducidad() {
        return caducidad;
    }

    @Override
    public int getCalorias() {
        return (int) (tipoVino.getGradosAlcohol() * FACTOR_CALORIAS_ALCOHOL);
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
        return "Vino{marca=" + getMarca() +
                ", tipo=" + tipoVino +
                ", precio=" + getPrecio() +
                ", descuento=" + descuento + "}";
    }
}
