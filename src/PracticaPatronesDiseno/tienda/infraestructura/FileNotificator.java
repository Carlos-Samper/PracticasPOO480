package PracticaPatronesDiseno.tienda.infraestructura;

import PracticaPatronesDiseno.tienda.dominio.Order;
import PracticaPatronesDiseno.tienda.dominio.OrderObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileNotificator implements OrderObserver {
    private String fileRoute;

    public FileNotificator(String fileRoute) {
        this.fileRoute = fileRoute;
    }

    @Override
    public void notify(Order order) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileRoute, true))) {
            writer.println("Order Confirmado - ID: " + order.getId() + " - Total: " + String.format("%.2f", order.getTotalImport()));
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}
