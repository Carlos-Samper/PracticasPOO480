package PracticaPatronesDiseno.tienda.dominio;

public interface Order {
    String getId();
    double getBaseImport();
    double getTotalImport();
    String getDescripcion();
}
