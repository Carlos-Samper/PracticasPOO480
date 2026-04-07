package Practica1;

public abstract class Producto {

    private String marca;
    private float precio;

    protected Producto(String marca, float precio) {
        this.marca  = marca;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public abstract String toString();
}
