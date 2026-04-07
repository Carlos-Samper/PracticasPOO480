package Practica2.Parking.dominio;

public class VehiculoResidente extends Vehiculo {

    public static final double TARIFA_POR_MINUTO = 0.002;

    private int minutosAcumulados;

    public VehiculoResidente(String matricula) {
        super(matricula);
        this.minutosAcumulados = 0;
    }

    @Override
    protected ResultadoSalida procesarSalida(Estancia estancia) {
        minutosAcumulados += estancia.getMinutos();
        return new ResultadoSalida(
                getMatricula(),
                getTipoVehiculo(),
                estancia.getMinutos(),
                0.0,
                "Salida registrada. El tiempo se ha acumulado para el pago mensual."
        );
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.RESIDENTE;
    }

    public int getMinutosAcumulados() {
        return minutosAcumulados;
    }

    public double getImportePendiente() {
        return minutosAcumulados * TARIFA_POR_MINUTO;
    }

    public void reiniciarMes() {
        minutosAcumulados = 0;
    }
}
