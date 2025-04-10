package View;

import Model.QualityDocument;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CustomReportView extends JFrame {
    private JComboBox<String> tipoReporteCombo;
    private JSpinner fromDatePicker;
    private JSpinner toDatePicker;

    private JTextField categoriaInsumoField;
    private JTextField stockField;

    private JTextField tipoProblemaField;
    private JTextField estadoReclamoField;

    private JTextField personalField;
    private JTextField turnoField;

    private JTextField autorField;

    private JButton btnGenerar;
    private JButton btnVolver;

    private JTable resultadosTable;
    private DefaultTableModel tableModel;

    public CustomReportView() {
        setTitle("Generador de Reportes Personalizados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        tipoReporteCombo = new JComboBox<>(new String[]{
                "Ventas", "Inventario", "Productos más vendidos",
                "Desempeño del personal", "Reporte de reclamos/problemas", "Reporte personalizado"
        });

        fromDatePicker = new JSpinner(new SpinnerDateModel());
        toDatePicker = new JSpinner(new SpinnerDateModel());
        fromDatePicker.setEditor(new JSpinner.DateEditor(fromDatePicker, "yyyy-MM-dd"));
        toDatePicker.setEditor(new JSpinner.DateEditor(toDatePicker, "yyyy-MM-dd"));

        categoriaInsumoField = new JTextField();
        stockField = new JTextField();

        tipoProblemaField = new JTextField();
        estadoReclamoField = new JTextField();

        personalField = new JTextField();
        turnoField = new JTextField();

        autorField = new JTextField();

        btnGenerar = new JButton("Generar Reporte");
        btnVolver = new JButton("Volver al Registro");

        panel.add(new JLabel("Tipo de Reporte:"));
        panel.add(tipoReporteCombo);

        panel.add(new JLabel("Desde:"));
        panel.add(fromDatePicker);

        panel.add(new JLabel("Hasta:"));
        panel.add(toDatePicker);

        panel.add(new JLabel("Categoría de Insumo:"));
        panel.add(categoriaInsumoField);

        panel.add(new JLabel("Stock:"));
        panel.add(stockField);

        panel.add(new JLabel("Tipo de Problema:"));
        panel.add(tipoProblemaField);

        panel.add(new JLabel("Estado del Reclamo:"));
        panel.add(estadoReclamoField);

        panel.add(new JLabel("Personal:"));
        panel.add(personalField);

        panel.add(new JLabel("Turno:"));
        panel.add(turnoField);

        panel.add(new JLabel("Autor:"));
        panel.add(autorField);

        panel.add(btnGenerar);
        panel.add(btnVolver);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Título", "Autor", "Categoría", "Fecha", "Archivo"});
        resultadosTable = new JTable(tableModel);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultadosTable), BorderLayout.CENTER);
    }

    public JComboBox<String> getTipoReporteCombo() {
        return tipoReporteCombo;
    }

    public LocalDate getFromDate() {
        return convertToLocalDate((Date) fromDatePicker.getValue());
    }

    public LocalDate getToDate() {
        return convertToLocalDate((Date) toDatePicker.getValue());
    }

    public String getCategoriaInsumo() {
        return categoriaInsumoField.getText();
    }

    public String getStock() {
        return stockField.getText();
    }

    public String getTipoProblema() {
        return tipoProblemaField.getText();
    }

    public String getEstadoReclamo() {
        return estadoReclamoField.getText();
    }

    public String getPersonal() {
        return personalField.getText();
    }

    public String getTurno() {
        return turnoField.getText();
    }

    public String getAutor() {
        return autorField.getText();
    }

    public JButton getBtnGenerar() {
        return btnGenerar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void mostrarResultados(List<QualityDocument> documentos) {
        tableModel.setRowCount(0);
        for (QualityDocument doc : documentos) {
            tableModel.addRow(new Object[]{
                    doc.getTitle(), doc.getAuthor(),
                    doc.getCategory(), doc.getDate(), doc.getFilePath()
            });
        }
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void goBackToRegistration() {
        this.dispose();
        new DocumentRegistrationView().setVisible(true);
    }
}
