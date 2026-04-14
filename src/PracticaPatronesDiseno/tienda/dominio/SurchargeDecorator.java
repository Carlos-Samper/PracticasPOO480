package PracticaPatronesDiseno.tienda.dominio;

public class SurchargeDecorator extends OrderDecorator {
    private double surcharge;

    public SurchargeDecorator(Order orderWrapped, double surcharge) {
        super(orderWrapped);
        this.surcharge = surcharge;
    }

    @Override
    public double getTotalImport() {
        return super.getTotalImport() + surcharge;
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Recargo (" + surcharge + ")";
    }
}
