package Practica1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static void mostrarDetergentes() {
        System.out.println("=== DETERGENTES ===");

        List<Producto> detergentes = List.of(
                crearDetergente("Ariel", 5.99f, 1.5f,  "Plástico", 10f),
                crearDetergente("Fairy", 3.49f, 0.75f, "Cristal",  0f)
        );

        detergentes.forEach(System.out::println);
        System.out.println();
    }

    private static Detergente crearDetergente(String marca, float precio,
                                              float volumen, String envase,
                                              float descuento) {
        Detergente d = new Detergente(marca, precio);
        d.setVolumen(volumen);
        d.setTipoEnvase(envase);
        d.setDescuento(descuento);
        return d;
    }

    private static void mostrarCereales() {
        System.out.println("=== CEREALES ===");

        List<Producto> cereales = List.of(
                new Cereales("Kellogs",   2.99f, TipoCereal.MAIZ),
                new Cereales("Hacendado", 1.89f, TipoCereal.ESPELTA),
                new Cereales("Nestlé",    3.20f, TipoCereal.TRIGO)
        );

        cereales.forEach(System.out::println);
        System.out.println();
    }

    private static void mostrarVinos() {
        System.out.println("=== VINOS ===");

        List<Producto> vinos = List.of(
                crearVino("Marqués de Riscal", TipoVino.TINTO,   12.90f, 0.75f, "Botella vidrio", 15f),
                crearVino("Martín Codax",      TipoVino.BLANCO,   9.50f, 0.75f, "Botella vidrio",  0f),
                crearVino("Freixenet",         TipoVino.ESPUMOSO, 8.99f, 0.75f, "Botella vidrio",  5f)
        );

        vinos.forEach(System.out::println);
        System.out.println();
    }

    private static Vino crearVino(String marca, TipoVino tipo, float precio,
                                  float volumen, String envase, float descuento) {
        Vino v = new Vino(marca, tipo, precio);
        v.setVolumen(volumen);
        v.setTipoEnvase(envase);
        v.setDescuento(descuento);
        return v;
    }

    private static void mostrarSumaCalorias() {
        System.out.println("=== SUMA DE CALORIAS ===");

        List<Alimento> alimentos = new ArrayList<>();
        alimentos.add(new Cereales("Kellogs",    2.99f, TipoCereal.MAIZ));
        alimentos.add(new Cereales("Hacendado",  1.89f, TipoCereal.ESPELTA));
        alimentos.add(new Cereales("Nestlé",     3.20f, TipoCereal.TRIGO));
        alimentos.add(new Vino("Don Rioja", TipoVino.TINTO,   12.90f));
        alimentos.add(new Vino("Flor de Mar",      TipoVino.BLANCO,   9.50f));
        alimentos.add(new Vino("Freixenet",         TipoVino.ESPUMOSO, 8.99f));

        alimentos.forEach(a ->
                System.out.printf("  %-22s → %d cal%n",
                        ((Producto) a).getMarca(), a.getCalorias())
        );

        System.out.printf("%nTotal de calorías: %d%n", calcularTotalCalorias(alimentos));
    }


    private static int calcularTotalCalorias(List<Alimento> alimentos) {
        return alimentos.stream()
                .mapToInt(Alimento::getCalorias)
                .sum();
    }

    public static void main(String[] args) {
        mostrarDetergentes();
        mostrarCereales();
        mostrarVinos();
        mostrarSumaCalorias();
    }
}
