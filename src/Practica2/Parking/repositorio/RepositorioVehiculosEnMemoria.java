package Practica2.Parking.repositorio;

import Practica2.Parking.dominio.Vehiculo;
import Practica2.Parking.dominio.VehiculoOficial;
import Practica2.Parking.dominio.VehiculoResidente;
import Practica2.Parking.excepcion.VehiculoDuplicadoException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioVehiculosEnMemoria implements RepositorioVehiculos {

    private final Map<String, Vehiculo> vehiculos;

    public RepositorioVehiculosEnMemoria() {
        this.vehiculos = new LinkedHashMap<>();
    }

    @Override
    public Optional<Vehiculo> buscarPorMatricula(String matricula) {
        if (matricula == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(vehiculos.get(Vehiculo.normalizarMatricula(matricula)));
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehiculo es obligatorio.");
        }
        if (vehiculos.containsKey(vehiculo.getMatricula())) {
            throw new VehiculoDuplicadoException("Ya existe un vehiculo registrado con la matricula " + vehiculo.getMatricula() + ".");
        }
        vehiculos.put(vehiculo.getMatricula(), vehiculo);
    }

    @Override
    public Collection<Vehiculo> obtenerTodos() {
        return vehiculos.values();
    }

    @Override
    public Collection<VehiculoOficial> obtenerOficiales() {
        return vehiculos.values().stream()
                .filter(VehiculoOficial.class::isInstance)
                .map(VehiculoOficial.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<VehiculoResidente> obtenerResidentes() {
        return vehiculos.values().stream()
                .filter(VehiculoResidente.class::isInstance)
                .map(VehiculoResidente.class::cast)
                .collect(Collectors.toList());
    }
}
