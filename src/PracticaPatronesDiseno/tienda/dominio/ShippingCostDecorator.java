package PracticaPatronesDiseno.tienda.dominio;

public class ShippingCostDecorator extends OrderDecorator {
    private double shippingCost;

    public ShippingCostDecorator(Order orderWrapped, double shippingCost) {
        super(orderWrapped);
        this.shippingCost = shippingCost;
    }

    @Override
    public double getTotalImport() {
        return super.getTotalImport() + shippingCost;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Envío (" + shippingCost + ")";
    }
}
