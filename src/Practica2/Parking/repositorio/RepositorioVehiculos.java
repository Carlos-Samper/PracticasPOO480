package Practica2.Parking.repositorio;

import Practica2.Parking.dominio.Vehiculo;
import Practica2.Parking.dominio.VehiculoOficial;
import Practica2.Parking.dominio.VehiculoResidente;

import java.util.Collection;
import java.util.Optional;

public interface RepositorioVehiculos {

    Optional<Vehiculo> buscarPorMatricula(String matricula);

    void guardar(Vehiculo vehiculo);

    Collection<Vehiculo> obtenerTodos();

    Collection<VehiculoOficial> obtenerOficiales();

    Collection<VehiculoResidente> obtenerResidentes();
}
