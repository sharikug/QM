package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class ReporteVista extends JFrame {
    private JComboBox<String> comboTipoReporte;
    private JSpinner fechaDesde, fechaHasta;
    private JPanel panelFiltros, panelDinamico;
    private JButton btnGenerar, btnExportarPDF, btnExportarExcel;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public ReporteVista() {
        setTitle("Generador de Reportes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2));

        comboTipoReporte = new JComboBox<>(new String[]{
                "Ventas", "Inventario", "Productos más vendidos",
                "Desempeño del personal", "Reporte de reclamos/problemas", "Reporte personalizado"
        });

        fechaDesde = new JSpinner(new SpinnerDateModel());
        fechaHasta = new JSpinner(new SpinnerDateModel());
        fechaDesde.setEditor(new JSpinner.DateEditor(fechaDesde, "yyyy-MM-dd"));
        fechaHasta.setEditor(new JSpinner.DateEditor(fechaHasta, "yyyy-MM-dd"));

        panelSuperior.add(new JLabel("Tipo de reporte:"));
        panelSuperior.add(comboTipoReporte);
        panelSuperior.add(new JLabel("Desde:"));
        panelSuperior.add(fechaDesde);
        panelSuperior.add(new JLabel("Hasta:"));
        panelSuperior.add(fechaHasta);

        add(panelSuperior, BorderLayout.NORTH);

        // --- Panel de filtros dinámicos
        panelFiltros = new JPanel(new BorderLayout());
        panelDinamico = new JPanel(); // Se reemplazará dinámicamente
        panelFiltros.add(new JLabel("Filtros adicionales:"), BorderLayout.NORTH);
        panelFiltros.add(panelDinamico, BorderLayout.CENTER);
        add(panelFiltros, BorderLayout.WEST);

        // --- Tabla de resultados
        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- Botones de acción
        JPanel panelBotones = new JPanel();
        btnGenerar = new JButton("Generar Reporte");
        btnExportarPDF = new JButton("Exportar a PDF");
        btnExportarExcel = new JButton("Exportar a Excel");

        panelBotones.add(btnGenerar);
        panelBotones.add(btnExportarPDF);
        panelBotones.add(btnExportarExcel);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getters para controlador
    public String getTipoReporte() {
        return (String) comboTipoReporte.getSelectedItem();
    }

    public Date getFechaDesde() {
        return (Date) fechaDesde.getValue();
    }

    public Date getFechaHasta() {
        return (Date) fechaHasta.getValue();
    }

    public void setPanelDinamico(JPanel nuevo) {
        panelDinamico.removeAll();
        panelDinamico.setLayout(new BorderLayout());
        panelDinamico.add(nuevo, BorderLayout.CENTER);
        panelDinamico.revalidate();
        panelDinamico.repaint();
    }

    public void setModeloTabla(String[] columnas, Object[][] datos) {
        modeloTabla.setDataVector(datos, columnas);
    }

    public JButton getBtnGenerar() { return btnGenerar; }
    public JButton getBtnExportarPDF() { return btnExportarPDF; }
    public JButton getBtnExportarExcel() { return btnExportarExcel; }
    public JComboBox<String> getComboTipoReporte() { return comboTipoReporte; }
}
