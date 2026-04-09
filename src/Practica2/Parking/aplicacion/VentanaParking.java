package Practica2.Parking.aplicacion;

import Practica2.Parking.dominio.ResultadoSalida;
import Practica2.Parking.servicio.ServicioParking;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaParking extends JFrame {

    private final ServicioParking servicioParking;
    private final JTextArea areaResultado;

    public VentanaParking(ServicioParking servicioParking) {
        this.servicioParking = servicioParking;
        this.areaResultado = new JTextArea();
        inicializar();
    }

    private void inicializar() {
        setTitle("Practica 2 - Parking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 8, 8));

        JButton botonRegistrarEntrada = new JButton("Registrar entrada");
        JButton botonRegistrarSalida = new JButton("Registrar salida");
        JButton botonAltaOficial = new JButton("Dar de alta vehiculo oficial");
        JButton botonAltaResidente = new JButton("Dar de alta vehiculo residente");
        JButton botonComenzarMes = new JButton("Comenzar mes");
        JButton botonInforme = new JButton("Generar informe de pagos de residentes");
        JButton botonMostrarVehiculos = new JButton("Mostrar vehiculos registrados");

        botonRegistrarEntrada.addActionListener(e -> registrarEntrada());
        botonRegistrarSalida.addActionListener(e -> registrarSalida());
        botonAltaOficial.addActionListener(e -> darDeAltaVehiculoOficial());
        botonAltaResidente.addActionListener(e -> darDeAltaVehiculoResidente());
        botonComenzarMes.addActionListener(e -> comenzarMes());
        botonInforme.addActionListener(e -> generarInforme());
        botonMostrarVehiculos.addActionListener(e -> mostrarVehiculos());

        panelBotones.add(botonRegistrarEntrada);
        panelBotones.add(botonRegistrarSalida);
        panelBotones.add(botonAltaOficial);
        panelBotones.add(botonAltaResidente);
        panelBotones.add(botonComenzarMes);
        panelBotones.add(botonInforme);
        panelBotones.add(botonMostrarVehiculos);

        areaResultado.setEditable(false);
        areaResultado.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        add(panelBotones, BorderLayout.WEST);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
    }

    private void registrarEntrada() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion(() -> {
            servicioParking.registrarEntrada(matricula);
            mostrarMensaje("Entrada registrada correctamente para la matricula " + matricula.toUpperCase() + ".");
        });
    }

    private void registrarSalida() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion(() -> {
            ResultadoSalida resultado = servicioParking.registrarSalida(matricula);
            mostrarMensaje(
                    "Salida registrada correctamente.\n" +
                    "Matricula: " + resultado.getMatricula() + "\n" +
                    "Tipo: " + resultado.getTipoVehiculo() + "\n" +
                    "Minutos estacionados: " + resultado.getMinutosEstacionados() + "\n" +
                    "Importe: " + String.format("%.2f EUR", resultado.getImporte()) + "\n" +
                    "Detalle: " + resultado.getMensaje()
            );
        });
    }

    private void darDeAltaVehiculoOficial() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo oficial.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion(() -> {
            servicioParking.darDeAltaVehiculoOficial(matricula);
            mostrarMensaje("Vehiculo oficial dado de alta correctamente: " + matricula.toUpperCase());
        });
    }

    private void darDeAltaVehiculoResidente() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo residente.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion(() -> {
            servicioParking.darDeAltaVehiculoResidente(matricula);
            mostrarMensaje("Vehiculo residente dado de alta correctamente: " + matricula.toUpperCase());
        });
    }

    private void comenzarMes() {
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Se eliminaran las estancias de oficiales y se pondran a cero los minutos de residentes. Quieres continuar?",
                "Confirmar comienzo de mes",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        ejecutarAccion(() -> {
            servicioParking.comenzarMes();
            mostrarMensaje("Proceso de comienzo de mes realizado correctamente.");
        });
    }

    private void generarInforme() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Selecciona el fichero TXT de destino");

        int opcion = selector.showSaveDialog(this);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        ejecutarAccion(() -> {
            String rutaSeleccionada = selector.getSelectedFile().getAbsolutePath();
            String ruta = rutaSeleccionada.toLowerCase().endsWith(".txt") ? rutaSeleccionada : rutaSeleccionada + ".txt";
            servicioParking.generarInformePagosResidentes(ruta);
            mostrarMensaje("Informe TXT generado correctamente en:\n" + ruta);
        });
    }

    private void mostrarVehiculos() {
        ejecutarAccion(() -> mostrarMensaje(servicioParking.obtenerResumenVehiculos()));
    }

    private String pedirTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(this, mensaje);
        if (texto == null) {
            return null;
        }
        if (texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes introducir un valor valido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return texto.trim();
    }

    private void ejecutarAccion(AccionConExcepcion accion) {
        try {
            accion.ejecutar();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error de E/S", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMensaje(String mensaje) {
        areaResultado.setText(mensaje);
    }

    @FunctionalInterface
    private interface AccionConExcepcion {
        void ejecutar() throws Exception;
    }
}
