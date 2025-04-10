package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportView extends JFrame {

    private JComboBox<String> reportTypeCombo;
    private JSpinner fromDateSpinner;
    private JSpinner toDateSpinner;
    private JButton generateButton;
    private JTable resultsTable;

    private JTextField fromDateField;
    private JTextField toDateField;

    // Filtros dinámicos
    private JComboBox<String> categoryFilter;
    private JTextField stockFilter;

    private JComboBox<String> problemTypeFilter;
    private JComboBox<String> problemStatusFilter;

    private JComboBox<String> staffFilter;
    private JComboBox<String> shiftFilter;

    private JPanel filterPanel;

    public ReportView() {
        setTitle("Quality Reports");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        reportTypeCombo = new JComboBox<>(new String[]{
                "Sales", "Inventory", "Top Selling Products", "Staff Performance", "Complaints", "Custom Report"
        });
        fromDateSpinner = new JSpinner(new SpinnerDateModel());
        toDateSpinner = new JSpinner(new SpinnerDateModel());

        fromDateSpinner.setEditor(new JSpinner.DateEditor(fromDateSpinner, "yyyy-MM-dd"));
        toDateSpinner.setEditor(new JSpinner.DateEditor(toDateSpinner, "yyyy-MM-dd"));

        topPanel.add(new JLabel("Report Type:"));
        topPanel.add(reportTypeCombo);
        topPanel.add(new JLabel("From:"));
        topPanel.add(fromDateSpinner);
        topPanel.add(new JLabel("To:"));
        topPanel.add(toDateSpinner);

        add(topPanel, BorderLayout.NORTH);

        // Filtro dinámico
        filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        initFilters();
        add(filterPanel, BorderLayout.CENTER);

        // Botón de generación
        generateButton = new JButton("Generate Report");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tabla de resultados
        resultsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.EAST);
        scrollPane.setPreferredSize(new Dimension(400, 600));

        // Lógica para mostrar filtros según el tipo de reporte
        reportTypeCombo.addActionListener(e -> updateDynamicFilters());

        setVisible(true);
    }

    private void initFilters() {
        // Inventario
        categoryFilter = new JComboBox<>(new String[]{"All", "Raw Materials", "Packaging", "Others"});
        stockFilter = new JTextField(5);

        // Reclamos
        problemTypeFilter = new JComboBox<>(new String[]{"All", "Hygiene", "Delays", "Service", "Other"});
        problemStatusFilter = new JComboBox<>(new String[]{"All", "Resolved", "Pending"});

        // Desempeño
        staffFilter = new JComboBox<>(new String[]{"All", "John", "Maria", "Pedro"});
        shiftFilter = new JComboBox<>(new String[]{"All", "Morning", "Afternoon", "Night"});

        updateDynamicFilters(); // mostrar los filtros iniciales
    }

    private void updateDynamicFilters() {
        filterPanel.removeAll();
        String selected = (String) reportTypeCombo.getSelectedItem();

        if ("Inventory".equals(selected)) {
            filterPanel.add(new JLabel("Category:"));
            filterPanel.add(categoryFilter);
            filterPanel.add(new JLabel("Stock >="));
            filterPanel.add(stockFilter);
        } else if ("Complaints".equals(selected)) {
            filterPanel.add(new JLabel("Problem Type:"));
            filterPanel.add(problemTypeFilter);
            filterPanel.add(new JLabel("Status:"));
            filterPanel.add(problemStatusFilter);
        } else if ("Staff Performance".equals(selected)) {
            filterPanel.add(new JLabel("Staff:"));
            filterPanel.add(staffFilter);
            filterPanel.add(new JLabel("Shift:"));
            filterPanel.add(shiftFilter);
        }

        filterPanel.revalidate();
        filterPanel.repaint();
    }

    // === Getters para el controlador ===

    public JComboBox<String> getReportTypeCombo() {
        return reportTypeCombo;
    }

//    public Date getFromDate() {
//        return (Date) fromDateSpinner.getValue();
//    }
//
//    public Date getToDate() {
//        return (Date) toDateSpinner.getValue();
//    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JTable getResultTable() {
        return resultsTable;
    }

    public String getSelectedCategory() {
        return (String) categoryFilter.getSelectedItem();
    }

    public String getStockFilterValue() {
        return stockFilter.getText();
    }

    public String getProblemType() {
        return (String) problemTypeFilter.getSelectedItem();
    }

    public String getProblemStatus() {
        return (String) problemStatusFilter.getSelectedItem();
    }

    public String getStaffName() {
        return (String) staffFilter.getSelectedItem();
    }

    public String getShift() {
        return (String) shiftFilter.getSelectedItem();
    }

    public void updateTable(Object[][] data, String[] columns) {
        DefaultTableModel model = new DefaultTableModel(data, columns);
        resultsTable.setModel(model);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public LocalDate getFromDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fromDateField.getText(), formatter);
        } catch (DateTimeParseException e) {
            showMessage("Invalid 'from' date format. Use YYYY-MM-DD.");
            return null;
        }
    }

    public LocalDate getToDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(toDateField.getText(), formatter);
        } catch (DateTimeParseException e) {
            showMessage("Invalid 'to' date format. Use YYYY-MM-DD.");
            return null;
        }
    }
}
