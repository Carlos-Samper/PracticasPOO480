package Practica1;

public enum TipoCereal {

    ESPELTA(5),
    MAIZ(8),
    TRIGO(12),
    OTROS(15);

    private final int calorias;

    TipoCereal(int calorias) {
        this.calorias = calorias;
    }

    public int getCalorias() {
        return calorias;
    }
}
