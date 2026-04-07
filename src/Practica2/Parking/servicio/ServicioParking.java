package Practica2.Parking.servicio;

import Practica2.Parking.dominio.ResultadoSalida;
import Practica2.Parking.dominio.Vehiculo;
import Practica2.Parking.dominio.VehiculoNoResidente;
import Practica2.Parking.dominio.VehiculoOficial;
import Practica2.Parking.dominio.VehiculoResidente;
import Practica2.Parking.infraestructura.GeneradorInformeResidentes;
import Practica2.Parking.infraestructura.ProveedorTiempo;
import Practica2.Parking.repositorio.RepositorioVehiculos;

import java.io.IOException;

public class ServicioParking {

    private final RepositorioVehiculos repositorioVehiculos;
    private final ProveedorTiempo proveedorTiempo;
    private final GeneradorInformeResidentes generadorInformeResidentes;

    public ServicioParking(RepositorioVehiculos repositorioVehiculos,
                           ProveedorTiempo proveedorTiempo,
                           GeneradorInformeResidentes generadorInformeResidentes) {
        this.repositorioVehiculos = repositorioVehiculos;
        this.proveedorTiempo = proveedorTiempo;
        this.generadorInformeResidentes = generadorInformeResidentes;
    }

    public void darDeAltaVehiculoOficial(String matricula) {
        repositorioVehiculos.guardar(new VehiculoOficial(matricula));
    }

    public void darDeAltaVehiculoResidente(String matricula) {
        repositorioVehiculos.guardar(new VehiculoResidente(matricula));
    }

    public void registrarEntrada(String matricula) {
        Vehiculo vehiculo = repositorioVehiculos.buscarPorMatricula(matricula)
                .orElseGet(() -> crearVehiculoNoResidente(matricula));

        vehiculo.registrarEntrada(proveedorTiempo.ahora());
    }

    public ResultadoSalida registrarSalida(String matricula) {
        Vehiculo vehiculo = repositorioVehiculos.buscarPorMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("No existe ningún vehículo registrado con la matrícula indicada."));

        return vehiculo.registrarSalida(proveedorTiempo.ahora());
    }

    public void comenzarMes() {
        repositorioVehiculos.obtenerOficiales().forEach(VehiculoOficial::reiniciarMes);
        repositorioVehiculos.obtenerResidentes().forEach(VehiculoResidente::reiniciarMes);
    }

    public void generarInformePagosResidentes(String nombreFichero) throws IOException {
        generadorInformeResidentes.generar(nombreFichero, repositorioVehiculos.obtenerResidentes());
    }

    public String obtenerResumenVehiculos() {
        StringBuilder resumen = new StringBuilder();

        if (repositorioVehiculos.obtenerTodos().isEmpty()) {
            return "No hay vehículos registrados.";
        }

        for (Vehiculo vehiculo : repositorioVehiculos.obtenerTodos()) {
            resumen.append("Matrícula: ").append(vehiculo.getMatricula())
                    .append(" | Tipo: ").append(vehiculo.getTipoVehiculo())
                    .append(" | Dentro: ").append(vehiculo.estaDentro() ? "Sí" : "No");

            if (vehiculo instanceof VehiculoResidente residente) {
                resumen.append(" | Minutos acumulados: ").append(residente.getMinutosAcumulados())
                        .append(" | Importe pendiente: ")
                        .append(String.format("%.2f €", residente.getImportePendiente()));
            }

            if (vehiculo instanceof VehiculoOficial oficial) {
                resumen.append(" | Estancias registradas: ").append(oficial.getEstancias().size());
            }

            resumen.append(System.lineSeparator());
        }

        return resumen.toString();
    }

    private Vehiculo crearVehiculoNoResidente(String matricula) {
        VehiculoNoResidente vehiculo = new VehiculoNoResidente(matricula);
        repositorioVehiculos.guardar(vehiculo);
        return vehiculo;
    }
}
