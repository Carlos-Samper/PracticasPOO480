package Practica2.Parking.dominio;

public class VehiculoNoResidente extends Vehiculo {

    public static final double TARIFA_POR_MINUTO = 0.02;

    public VehiculoNoResidente(String matricula) {
        super(matricula);
    }

    @Override
    protected ResultadoSalida procesarSalida(Estancia estancia) {
        double importe = estancia.getMinutos() * TARIFA_POR_MINUTO;
        return new ResultadoSalida(
                getMatricula(),
                getTipoVehiculo(),
                estancia.getMinutos(),
                importe,
                String.format("Salida registrada. Importe a abonar: %.2f €", importe)
        );
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.NO_RESIDENTE;
    }
}
