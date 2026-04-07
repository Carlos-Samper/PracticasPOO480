package Practica2.Parking.dominio;

import Practica2.Parking.excepcion.EstadoVehiculoException;

import java.time.LocalDateTime;

public abstract class Vehiculo {

    private final String matricula;
    private LocalDateTime horaEntradaActual;

    protected Vehiculo(String matricula) {
        this.matricula = normalizarMatricula(matricula);
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getHoraEntradaActual() {
        return horaEntradaActual;
    }

    public boolean estaDentro() {
        return horaEntradaActual != null;
    }

    public void registrarEntrada(LocalDateTime horaEntrada) {
        if (horaEntrada == null) {
            throw new IllegalArgumentException("La hora de entrada es obligatoria.");
        }
        if (estaDentro()) {
            throw new EstadoVehiculoException("El vehículo " + matricula + " ya se encuentra dentro del aparcamiento.");
        }
        this.horaEntradaActual = horaEntrada;
    }

    public ResultadoSalida registrarSalida(LocalDateTime horaSalida) {
        if (horaSalida == null) {
            throw new IllegalArgumentException("La hora de salida es obligatoria.");
        }
        if (!estaDentro()) {
            throw new EstadoVehiculoException("El vehículo " + matricula + " no tiene una entrada registrada.");
        }

        Estancia estancia = new Estancia(horaEntradaActual, horaSalida);
        this.horaEntradaActual = null;
        return procesarSalida(estancia);
    }

    protected abstract ResultadoSalida procesarSalida(Estancia estancia);

    public abstract TipoVehiculo getTipoVehiculo();

    protected static String normalizarMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula es obligatoria.");
        }
        return matricula.trim().toUpperCase();
    }
}
