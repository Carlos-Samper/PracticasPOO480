package Practica1;

import java.util.Date;

public interface Alimento {
    void setCaducidad(Date caducidad);
    Date getCaducidad();
    int getCalorias();
}