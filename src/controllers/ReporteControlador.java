package controllers;

import views.ReporteVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReporteControlador {
    private ReporteVista vista;

    public ReporteControlador(ReporteVista vista) {
        this.vista = vista;

        // Cambiar filtros dinámicamente
        vista.getComboTipoReporte().addActionListener(this::cambiarFiltros);

        // Botón generar reporte
        vista.getBtnGenerar().addActionListener(this::generarReporte);

        // Botones exportar
        vista.getBtnExportarPDF().addActionListener(e -> exportar("pdf"));
        vista.getBtnExportarExcel().addActionListener(e -> exportar("excel"));
    }

    private void cambiarFiltros(ActionEvent e) {
        String tipo = vista.getTipoReporte();
        JPanel nuevoPanel = new JPanel();

        switch (tipo) {
            case "Ventas":
                nuevoPanel.setLayout(new GridLayout(3, 2));
                nuevoPanel.add(new JLabel("Método de pago:"));
                nuevoPanel.add(new JComboBox<>(new String[]{"Efectivo", "Tarjeta"}));
                nuevoPanel.add(new JLabel("Zona / Mesa:"));
                nuevoPanel.add(new JTextField());
                nuevoPanel.add(new JLabel("Mesero:"));
                nuevoPanel.add(new JTextField());
                break;
            case "Inventario":
                nuevoPanel.setLayout(new GridLayout(2, 2));
                nuevoPanel.add(new JLabel("Categoría:"));
                nuevoPanel.add(new JTextField());
                nuevoPanel.add(new JLabel("Stock mínimo:"));
                nuevoPanel.add(new JTextField());
                break;
            case "Reporte de reclamos/problemas":
                nuevoPanel.setLayout(new GridLayout(2, 2));
                nuevoPanel.add(new JLabel("Tipo de problema:"));
                nuevoPanel.add(new JTextField());
                nuevoPanel.add(new JLabel("Estado:"));
                nuevoPanel.add(new JComboBox<>(new String[]{"Pendiente", "Resuelto"}));
                break;
            case "Desempeño del personal":
                nuevoPanel.setLayout(new GridLayout(2, 2));
                nuevoPanel.add(new JLabel("Personal:"));
                nuevoPanel.add(new JTextField());
                nuevoPanel.add(new JLabel("Turno:"));
                nuevoPanel.add(new JComboBox<>(new String[]{"Mañana", "Tarde", "Noche"}));
                break;
        }

        vista.setPanelDinamico(nuevoPanel);
    }

    private void generarReporte(ActionEvent e) {
        // Aquí puedes simular datos
        String tipo = vista.getTipoReporte();
        String[] columnas = {"Dato 1", "Dato 2", "Dato 3"};
        Object[][] datos = {
                {"Ejemplo 1", "Valor 1", "Dato A"},
                {"Ejemplo 2", "Valor 2", "Dato B"},
                {"Ejemplo 3", "Valor 3", "Dato C"}
        };

        vista.setModeloTabla(columnas, datos);
    }

    private void exportar(String tipo) {
        JOptionPane.showMessageDialog(null, "Exportando a " + tipo.toUpperCase() + " (aún sin implementar)");
        // Aquí luego se usa Exportador.java
    }
}
