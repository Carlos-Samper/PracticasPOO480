package Practica2.Parking.infraestructura;

import Practica2.Parking.dominio.VehiculoResidente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class GeneradorInformeResidentes {

    public void generar(String nombreFichero, Collection<VehiculoResidente> residentes) throws IOException {
        if (nombreFichero == null || nombreFichero.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del fichero es obligatorio.");
        }

        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreFichero))) {
            escritor.printf("%-12s %28s %20s%n",
                    "Matrícula",
                    "Tiempo estacionado (min.)",
                    "Cantidad a pagar");

            for (VehiculoResidente residente : residentes) {
                escritor.printf("%-12s %28d %20.2f%n",
                        residente.getMatricula(),
                        residente.getMinutosAcumulados(),
                        residente.getImportePendiente());
            }
        }
    }
}
