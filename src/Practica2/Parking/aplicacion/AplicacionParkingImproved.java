package Practica2.Parking.aplicacion;

import Practica2.Parking.infraestructura.GeneradorInformeResidentes;
import Practica2.Parking.infraestructura.ProveedorTiempoSistema;
import Practica2.Parking.repositorio.RepositorioVehiculos;
import Practica2.Parking.repositorio.RepositorioVehiculosEnMemoria;
import Practica2.Parking.servicio.ServicioParking;

import javax.swing.SwingUtilities;

public class AplicacionParkingImproved {

    public static void main(String[] args) {
        RepositorioVehiculos repositorioVehiculos = new RepositorioVehiculosEnMemoria();
        ServicioParking servicioParking = new ServicioParking(
                repositorioVehiculos,
                new ProveedorTiempoSistema(),
                new GeneradorInformeResidentes()
        );

        SwingUtilities.invokeLater(() -> new VentanaParkingImproved(servicioParking).setVisible(true));
    }
}
