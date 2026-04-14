package PracticaPatronesDiseno.tienda.infraestructura;

import PracticaPatronesDiseno.tienda.dominio.OrderObserver;
import PracticaPatronesDiseno.tienda.dominio.Order;

public class ConsoleNotificator implements OrderObserver {
    @Override
    public void notify(Order order) {
        System.out.println("[Interno - Consola] Nuevo order confirmado. ID: " + order.getId() + " | Desc: " + order.getDescripcion() + " | Total: " + String.format("%.2f", order.getTotalImport()));
    }
}
