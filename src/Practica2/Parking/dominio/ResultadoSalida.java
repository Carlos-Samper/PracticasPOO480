package Practica2.Parking.dominio;

public class ResultadoSalida {

    private final String matricula;
    private final TipoVehiculo tipoVehiculo;
    private final int minutosEstacionados;
    private final double importe;
    private final String mensaje;

    public ResultadoSalida(String matricula, TipoVehiculo tipoVehiculo, int minutosEstacionados, double importe, String mensaje) {
        this.matricula = matricula;
        this.tipoVehiculo = tipoVehiculo;
        this.minutosEstacionados = minutosEstacionados;
        this.importe = importe;
        this.mensaje = mensaje;
    }

    public String getMatricula() {
        return matricula;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getMinutosEstacionados() {
        return minutosEstacionados;
    }

    public double getImporte() {
        return importe;
    }

    public String getMensaje() {
        return mensaje;
    }
}
