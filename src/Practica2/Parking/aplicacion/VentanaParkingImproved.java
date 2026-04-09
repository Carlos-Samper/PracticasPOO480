package Practica2.Parking.aplicacion;

import Practica2.Parking.dominio.ResultadoSalida;
import Practica2.Parking.servicio.ServicioParking;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VentanaParkingImproved extends JFrame {

    private static final Color COLOR_FONDO = new Color(13, 17, 23);
    private static final Color COLOR_SUPERFICIE = new Color(22, 28, 36);
    private static final Color COLOR_SUPERFICIE_SECUNDARIA = new Color(28, 35, 45);
    private static final Color COLOR_TITULO = new Color(232, 239, 247);
    private static final Color COLOR_SUBTITULO = new Color(165, 183, 203);
    private static final Color COLOR_PRIMARIO = new Color(0, 176, 255);
    private static final Color COLOR_PRIMARIO_HOVER = new Color(0, 210, 255);
    private static final Color COLOR_SECUNDARIO = new Color(35, 45, 58);
    private static final Color COLOR_SECUNDARIO_HOVER = new Color(47, 60, 77);
    private static final Color COLOR_TEXTO = new Color(201, 214, 230);
    private static final Color COLOR_BORDE = new Color(53, 65, 81);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final ServicioParking servicioParking;
    private final JTextArea areaEstado;
    private final JTextArea areaActividad;
    private final JLabel etiquetaEstado;
    private final List<JButton> botonesAccion;

    public VentanaParkingImproved(ServicioParking servicioParking) {
        this.servicioParking = servicioParking;
        this.areaEstado = crearAreaTextoMonoespaciada();
        this.areaActividad = crearAreaTextoNormal();
        this.etiquetaEstado = new JLabel("Listo", SwingConstants.LEFT);
        this.botonesAccion = new ArrayList<>();
        inicializar();
        refrescarVista();
    }

    private void inicializar() {
        configurarLookAndFeelBasico();
        setTitle("Practica 2 - Parking (Versión Premium)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 760));
        setLocationRelativeTo(null);

        JPanel raiz = new JPanel(new BorderLayout(12, 12));
        raiz.setBackground(COLOR_FONDO);
        raiz.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        raiz.add(crearCabecera(), BorderLayout.NORTH);
        raiz.add(crearPanelAcciones(), BorderLayout.WEST);
        raiz.add(crearPanelPrincipal(), BorderLayout.CENTER);
        raiz.add(crearBarraEstado(), BorderLayout.SOUTH);

        setContentPane(raiz);
        pack();
    }

    private JPanel crearCabecera() {
        JPanel cabecera = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(18, 26, 38),
                        getWidth(), getHeight(), new Color(10, 14, 21))
                );
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(0, 176, 255, 90),
                        getWidth(), 0, new Color(0, 176, 255, 10))
                );
                g2.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20);
                g2.dispose();
            }
        };
        cabecera.setOpaque(false);
        cabecera.setPreferredSize(new Dimension(1000, 100));
        cabecera.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));

        JLabel titulo = new JLabel("Panel de gestion del parking v2");
        titulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 28));
        titulo.setForeground(COLOR_TITULO);

        JLabel subtitulo = new JLabel("Versión mejorada tras investigar la documentación de JFrame");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitulo.setForeground(COLOR_SUBTITULO);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(subtitulo);

        cabecera.add(textos, BorderLayout.WEST);
        return cabecera;
    }

    private JPanel crearPanelAcciones() {
        JPanel lateral = new JPanel(new BorderLayout(0, 10));
        lateral.setBackground(COLOR_SUPERFICIE);
        lateral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)
        ));
        lateral.setPreferredSize(new Dimension(290, 400));

        JLabel titulo = new JLabel("Acciones");
        titulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        titulo.setForeground(COLOR_TITULO);
        lateral.add(titulo, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 1, 0, 8));
        grid.setOpaque(false);

        grid.add(crearBotonPrimario("Registrar entrada", this::registrarEntrada));
        grid.add(crearBotonPrimario("Registrar salida", this::registrarSalida));
        grid.add(crearBotonSecundario("Alta vehiculo oficial", this::darDeAltaVehiculoOficial));
        grid.add(crearBotonSecundario("Alta vehiculo residente", this::darDeAltaVehiculoResidente));
        grid.add(crearBotonSecundario("Comenzar mes", this::comenzarMes));
        grid.add(crearBotonSecundario("Generar informe", this::generarInforme));
        grid.add(crearBotonSecundario("Actualizar vista", this::refrescarVista));

        lateral.add(grid, BorderLayout.CENTER);
        return lateral;
    }

    private JSplitPane crearPanelPrincipal() {
        JPanel panelEstado = crearTarjeta("Estado de vehiculos", areaEstado);
        JPanel panelActividad = crearTarjeta("Actividad reciente", areaActividad);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelEstado, panelActividad);
        split.setResizeWeight(0.65);
        split.setBackground(COLOR_FONDO);
        split.setForeground(COLOR_BORDE);
        split.setBorder(BorderFactory.createEmptyBorder());
        split.setDividerSize(8);
        split.setContinuousLayout(true);
        return split;
    }

    private JPanel crearTarjeta(String titulo, JTextArea area) {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(COLOR_SUPERFICIE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JLabel etiquetaTitulo = new JLabel(titulo);
        etiquetaTitulo.setForeground(COLOR_TITULO);
        etiquetaTitulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 1));
        scroll.getViewport().setBackground(COLOR_SUPERFICIE_SECUNDARIA);
        scroll.setBackground(COLOR_SUPERFICIE_SECUNDARIA);

        panel.add(etiquetaTitulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearBarraEstado() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(COLOR_SUPERFICIE);
        barra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        etiquetaEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        etiquetaEstado.setForeground(COLOR_TEXTO);
        barra.add(etiquetaEstado, BorderLayout.WEST);

        JLabel marcaTiempo = new JLabel("Sesion iniciada: " + FORMATO_FECHA.format(LocalDateTime.now()));
        marcaTiempo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        marcaTiempo.setForeground(COLOR_SUBTITULO);
        barra.add(marcaTiempo, BorderLayout.EAST);
        return barra;
    }

    private JButton crearBotonPrimario(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(COLOR_PRIMARIO);
        boton.setForeground(new Color(8, 19, 29));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 223, 255), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        boton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setRolloverEnabled(true);
        boton.addActionListener(e -> accion.run());
        boton.addChangeListener(e -> boton.setBackground(
                boton.getModel().isRollover() ? COLOR_PRIMARIO_HOVER : COLOR_PRIMARIO
        ));
        botonesAccion.add(boton);
        return boton;
    }

    private JButton crearBotonSecundario(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(COLOR_SECUNDARIO);
        boton.setForeground(COLOR_TEXTO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setRolloverEnabled(true);
        boton.addChangeListener(e -> boton.setBackground(
                boton.getModel().isRollover() ? COLOR_SECUNDARIO_HOVER : COLOR_SECUNDARIO
        ));
        boton.addActionListener(e -> accion.run());
        botonesAccion.add(boton);
        return boton;
    }

    private JTextArea crearAreaTextoMonoespaciada() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        area.setLineWrap(false);
        area.setWrapStyleWord(false);
        area.setBackground(COLOR_SUPERFICIE_SECUNDARIA);
        area.setForeground(COLOR_TEXTO);
        area.setCaretColor(COLOR_PRIMARIO_HOVER);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return area;
    }

    private JTextArea crearAreaTextoNormal() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(COLOR_SUPERFICIE_SECUNDARIA);
        area.setForeground(COLOR_TEXTO);
        area.setCaretColor(COLOR_PRIMARIO_HOVER);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return area;
    }

    private void registrarEntrada() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion("Entrada registrada para " + matricula.toUpperCase(), () -> servicioParking.registrarEntrada(matricula));
    }

    private void registrarSalida() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion("Salida registrada para " + matricula.toUpperCase(), () -> {
            ResultadoSalida resultado = servicioParking.registrarSalida(matricula);
            agregarActividad(
                    "Salida: " + resultado.getMatricula() + " | Tipo: " + resultado.getTipoVehiculo() +
                            " | Minutos: " + resultado.getMinutosEstacionados() +
                            " | Importe: " + String.format("%.2f EUR", resultado.getImporte()) +
                            " | " + resultado.getMensaje()
            );
        });
    }

    private void darDeAltaVehiculoOficial() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo oficial.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion("Alta oficial completada: " + matricula.toUpperCase(),
                () -> servicioParking.darDeAltaVehiculoOficial(matricula));
    }

    private void darDeAltaVehiculoResidente() {
        String matricula = pedirTexto("Introduce la matricula del vehiculo residente.\nFormato valido: 0000-XXX, 0000XXX o 0000 XXX");
        if (matricula == null) {
            return;
        }

        ejecutarAccion("Alta residente completada: " + matricula.toUpperCase(),
                () -> servicioParking.darDeAltaVehiculoResidente(matricula));
    }

    private void comenzarMes() {
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Se reiniciaran residentes y estancias de oficiales. Quieres continuar?",
                "Confirmar comienzo de mes",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            agregarActividad("Comenzar mes cancelado por el usuario.");
            return;
        }

        ejecutarAccion("Comienzo de mes ejecutado correctamente.", servicioParking::comenzarMes);
    }

    private void generarInforme() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Selecciona el fichero TXT de destino");

        int opcion = selector.showSaveDialog(this);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            agregarActividad("Generacion de informe cancelada por el usuario.");
            return;
        }

        String rutaSeleccionada = selector.getSelectedFile().getAbsolutePath();
        String ruta = rutaSeleccionada.toLowerCase().endsWith(".txt") ? rutaSeleccionada : rutaSeleccionada + ".txt";

        ejecutarAccion("Informe TXT generado: " + ruta, () -> {
            servicioParking.generarInformePagosResidentes(ruta);
        });
    }

    private void refrescarVista() {
        areaEstado.setText(servicioParking.obtenerResumenVehiculos());
        if (areaEstado.getText().trim().isEmpty()) {
            areaEstado.setText("No hay vehiculos registrados.");
        }
        actualizarEstado("Vista actualizada");
    }

    private void ejecutarAccion(String mensajeExito, AccionConExcepcion accion) {
        cambiarEstadoCarga(true);
        try {
            accion.ejecutar();
            actualizarEstado(mensajeExito);
            agregarActividad(mensajeExito);
            refrescarVista();
        } catch (IOException exception) {
            mostrarError("Error de E/S: " + exception.getMessage());
        } catch (Exception exception) {
            mostrarError(exception.getMessage());
        } finally {
            cambiarEstadoCarga(false);
        }
    }

    private void actualizarEstado(String texto) {
        etiquetaEstado.setText(texto + " | " + FORMATO_FECHA.format(LocalDateTime.now()));
    }

    private void mostrarError(String mensaje) {
        actualizarEstado("Error");
        agregarActividad("ERROR: " + mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void agregarActividad(String mensaje) {
        String entrada = "[" + FORMATO_FECHA.format(LocalDateTime.now()) + "] " + mensaje + System.lineSeparator();
        areaActividad.append(entrada);
        areaActividad.setCaretPosition(areaActividad.getDocument().getLength());
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

    private void cambiarEstadoCarga(boolean enProceso) {
        for (JButton boton : botonesAccion) {
            boton.setEnabled(!enProceso);
        }
        setCursor(enProceso ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : Cursor.getDefaultCursor());
    }

    private void configurarLookAndFeelBasico() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("OptionPane.background", new ColorUIResource(COLOR_SUPERFICIE));
            UIManager.put("Panel.background", new ColorUIResource(COLOR_SUPERFICIE));
            UIManager.put("OptionPane.messageForeground", new ColorUIResource(COLOR_TEXTO));
            UIManager.put("Button.background", new ColorUIResource(COLOR_SECUNDARIO));
            UIManager.put("Button.foreground", new ColorUIResource(COLOR_TEXTO));
        } catch (Exception ignored) {
            // If this fails, keep default look and feel.
        }
    }

    @FunctionalInterface
    private interface AccionConExcepcion {
        void ejecutar() throws Exception;
    }
}
