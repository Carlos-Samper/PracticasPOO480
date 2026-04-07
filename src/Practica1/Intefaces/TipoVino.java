package Practica1.Intefaces;

public enum TipoVino {

    TINTO(13.5f),
    BLANCO(11.0f),
    ROSADO(12.0f),
    ESPUMOSO(11.5f),
    DULCE(10.0f);

    private final float gradosAlcohol;

    TipoVino(float gradosAlcohol) {
        this.gradosAlcohol = gradosAlcohol;
    }

    public float getGradosAlcohol() {
        return gradosAlcohol;
    }
}
