package PracticaPatronesDiseno.tienda.dominio;

public abstract class OrderDecorator implements Order {
    protected Order orderWrapped;

    public OrderDecorator(Order orderWrapped) {
        this.orderWrapped = orderWrapped;
    }

    @Override
    public String getId() {
        return orderWrapped.getId();
    }

    @Override
    public double getBaseImport() {
        return orderWrapped.getBaseImport();
    }

    @Override
    public double getTotalImport() {
        return orderWrapped.getTotalImport();
    }

    @Override
    public String getDescripcion() {
        return orderWrapped.getDescripcion();
    }
}
