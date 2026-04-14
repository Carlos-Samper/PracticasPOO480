package PracticaPatronesDiseno.tienda.dominio;

public class BaseOrder implements Order {
    private String id;
    private double baseImport;

    public BaseOrder(String id, double baseImport) {
        this.id = id;
        this.baseImport = baseImport;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getBaseImport() {
        return baseImport;
    }

    @Override
    public double getTotalImport() {
        return baseImport;
    }

    @Override
    public String getDescripcion() {
        return "Order " + id;
    }
}
