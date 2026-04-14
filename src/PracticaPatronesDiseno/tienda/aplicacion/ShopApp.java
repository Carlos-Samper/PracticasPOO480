package PracticaPatronesDiseno.tienda.aplicacion;

import PracticaPatronesDiseno.tienda.dominio.*;
import PracticaPatronesDiseno.tienda.infraestructura.ConsoleNotificator;
import PracticaPatronesDiseno.tienda.infraestructura.MailNotificator;
import PracticaPatronesDiseno.tienda.infraestructura.FileNotificator;
import PracticaPatronesDiseno.tienda.servicio.OrderService;

public class ShopApp {
    public static void main(String[] args) {
        // 1. Configuración del servicio y observadores
        OrderService orderService = new OrderService();
        
        orderService.addSubscriber(new MailNotificator("cliente@example.com"));
        orderService.addSubscriber(new FileNotificator("pedidos_confirmados.txt"));
        orderService.addSubscriber(new ConsoleNotificator());

        // 2. Crear un pedido con importe base (Aplicando patrón Decorator)
        Order miOrder = new BaseOrder("PED-001", 100.0);
        
        // 3. Aplicar operaciones (Decoradores)
        miOrder = new DiscountDecorator(miOrder, 10.0);     // 10% de descuento
        miOrder = new TaxDecorator(miOrder, 21.0);      // 21% de impuesto
        miOrder = new ShippingCostDecorator(miOrder, 5.99);   // 5.99 de envío
        miOrder = new SurchargeDecorator(miOrder, 2.0);        // 2.0 de recargo

        // 4. Confirmar el pedido (El gestor notifica a los observadores)
        orderService.confirmOrder(miOrder);
    }
}
