package PracticaPatronesDiseno.tienda.servicio;

import PracticaPatronesDiseno.tienda.dominio.Order;
import PracticaPatronesDiseno.tienda.dominio.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<OrderObserver> subscribers;

    public OrderService() {
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(OrderObserver subscriber) {
        subscribers.add(subscriber);
    }

    public void confirmOrder(Order order) {
        System.out.println("Confirmando order: " + order.getId());
        notifySubscriber(order);
    }

    private void notifySubscriber(Order order) {
        for (OrderObserver subscriber : subscribers) {
            subscriber.notify(order);
        }
    }
}
