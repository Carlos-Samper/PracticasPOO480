package Practica1.Intefaces;

import java.util.Date;

public interface Alimento {
    void setCaducidad(Date caducidad);
    Date getCaducidad();
    int getCalorias();
}