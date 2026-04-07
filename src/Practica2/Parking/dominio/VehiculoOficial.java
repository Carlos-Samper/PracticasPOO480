package Practica2.Parking.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehiculoOficial extends Vehiculo {

    private final List<Estancia> estancias;

    public VehiculoOficial(String matricula) {
        super(matricula);
        this.estancias = new ArrayList<>();
    }

    @Override
    protected ResultadoSalida procesarSalida(Estancia estancia) {
        estancias.add(estancia);
        return new ResultadoSalida(
                getMatricula(),
                getTipoVehiculo(),
                estancia.getMinutos(),
                0.0,
                "Salida registrada. Vehículo oficial: no debe abonar importe."
        );
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.OFICIAL;
    }

    public List<Estancia> getEstancias() {
        return Collections.unmodifiableList(estancias);
    }

    public void reiniciarMes() {
        estancias.clear();
    }
}
