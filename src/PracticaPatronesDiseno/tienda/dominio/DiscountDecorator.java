package PracticaPatronesDiseno.tienda.dominio;

public class DiscountDecorator extends OrderDecorator {
    private double discountPercentage;

    public DiscountDecorator(Order orderWrapped, double discountPercentage) {
        super(orderWrapped);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getTotalImport() {
        return super.getTotalImport() * (1 - discountPercentage / 100.0);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Descuento " + discountPercentage + "%";
    }
}
