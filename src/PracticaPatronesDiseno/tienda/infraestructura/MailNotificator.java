package PracticaPatronesDiseno.tienda.infraestructura;

import PracticaPatronesDiseno.tienda.dominio.Order;
import PracticaPatronesDiseno.tienda.dominio.OrderObserver;

public class MailNotificator implements OrderObserver {
    private String mailTo;

    public MailNotificator(String emailDestino) {
        this.mailTo = emailDestino;
    }

    @Override
    public void notify(Order order) {
        System.out.println("[Email] Enviando correo a " + mailTo + " para el order " + order.getId() + ". Total: " + String.format("%.2f", order.getTotalImport()));
    }
}
