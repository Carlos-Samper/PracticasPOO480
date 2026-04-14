package PracticaPatronesDiseno.tienda.dominio;

public class TaxDecorator extends OrderDecorator {
    private double taxPercentage;

    public TaxDecorator(Order orderWrapped, double taxPercentage) {
        super(orderWrapped);
        this.taxPercentage = taxPercentage;
    }

    @Override
    public double getTotalImport() {
        return super.getTotalImport() * (1 + taxPercentage / 100.0);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Impuesto " + taxPercentage + "%";
    }
}
