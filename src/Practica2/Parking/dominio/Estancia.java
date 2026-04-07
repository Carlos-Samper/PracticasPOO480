package Practica2.Parking.dominio;

import java.time.Duration;
import java.time.LocalDateTime;

public class Estancia {

    private final LocalDateTime horaEntrada;
    private final LocalDateTime horaSalida;

    public Estancia(LocalDateTime horaEntrada, LocalDateTime horaSalida) {
        if (horaEntrada == null || horaSalida == null) {
            throw new IllegalArgumentException("Las horas de entrada y salida son obligatorias.");
        }
        if (horaSalida.isBefore(horaEntrada)) {
            throw new IllegalArgumentException("La hora de salida no puede ser anterior a la de entrada.");
        }
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public int getMinutos() {
        return (int) Duration.between(horaEntrada, horaSalida).toMinutes();
    }
}
