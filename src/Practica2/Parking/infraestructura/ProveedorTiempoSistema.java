package Practica2.Parking.infraestructura;

import java.time.LocalDateTime;

public class ProveedorTiempoSistema implements ProveedorTiempo {

    @Override
    public LocalDateTime ahora() {
        return LocalDateTime.now();
    }
}
