package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CustomReportView extends JFrame {

    private JComboBox<String> reportTypeCombo;
    private JSpinner fromDateSpinner, toDateSpinner;
    private JButton generateButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    // Filtros dinámicos
    private JComboBox<String> categoryCombo;
    private JComboBox<String> stockCombo;
    private JComboBox<String> problemTypeCombo;
    private JComboBox<String> statusCombo;
    private JComboBox<String> personnelCombo;
    private JComboBox<String> shiftCombo;

    private JPanel filtersPanel;

    public CustomReportView() {
        setTitle("Generate Reports");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        filtersPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Report type
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Report Type:"), gbc);

        gbc.gridx = 1;
        reportTypeCombo = new JComboBox<>(new String[]{
                "Ventas", "Inventario", "Productos más vendidos",
                "Desempeño del personal", "Reporte de reclamos/problemas",
                "Reporte personalizado"
        });
        mainPanel.add(reportTypeCombo, gbc);

        // Date range
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("From:"), gbc);

        gbc.gridx = 1;
        fromDateSpinner = new JSpinner(new SpinnerDateModel());
        fromDateSpinner.setEditor(new JSpinner.DateEditor(fromDateSpinner, "yyyy-MM-dd"));
        mainPanel.add(fromDateSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("To:"), gbc);

        gbc.gridx = 1;
        toDateSpinner = new JSpinner(new SpinnerDateModel());
        toDateSpinner.setEditor(new JSpinner.DateEditor(toDateSpinner, "yyyy-MM-dd"));
        mainPanel.add(toDateSpinner, gbc);

        // Dynamic filters panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(filtersPanel, gbc);

        // Generate button
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        generateButton = new JButton("Generate Report");
        mainPanel.add(generateButton, gbc);

        add(mainPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Title", "Author", "Category", "Date", "File Path"};
        tableModel = new DefaultTableModel(columns, 0);
        resultsTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(resultsTable);
        add(tableScroll, BorderLayout.CENTER);

        initDynamicFilters();
        reportTypeCombo.addActionListener(e -> updateDynamicFilters());

        setVisible(true);
    }

    private void initDynamicFilters() {
        categoryCombo = new JComboBox<>(new String[]{"Insumo A", "Insumo B"});
        stockCombo = new JComboBox<>(new String[]{"Bajo", "Medio", "Alto"});
        problemTypeCombo = new JComboBox<>(new String[]{"Retraso", "Calidad", "Otro"});
        statusCombo = new JComboBox<>(new String[]{"Resuelto", "Pendiente"});
        personnelCombo = new JComboBox<>(new String[]{"Juan", "Ana", "Carlos"});
        shiftCombo = new JComboBox<>(new String[]{"Mañana", "Tarde", "Noche"});
    }

    private void updateDynamicFilters() {
        filtersPanel.removeAll();
        String selected = (String) reportTypeCombo.getSelectedItem();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (selected.equals("Inventario")) {
            filtersPanel.add(new JLabel("Categoría de insumo:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(categoryCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            filtersPanel.add(new JLabel("Stock:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(stockCombo, gbc);
        } else if (selected.equals("Reporte de reclamos/problemas")) {
            filtersPanel.add(new JLabel("Tipo de problema:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(problemTypeCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            filtersPanel.add(new JLabel("Estado:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(statusCombo, gbc);
        } else if (selected.equals("Desempeño del personal")) {
            filtersPanel.add(new JLabel("Personal:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(personnelCombo, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            filtersPanel.add(new JLabel("Turno:"), gbc);
            gbc.gridx = 1;
            filtersPanel.add(shiftCombo, gbc);
        }

        filtersPanel.revalidate();
        filtersPanel.repaint();
    }

    public JComboBox<String> getReportTypeCombo() {
        return reportTypeCombo;
    }

    public LocalDate getDateFrom() {
        Date date = (Date) fromDateSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getDateTo() {
        Date date = (Date) toDateSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public void updateTable(java.util.List<Model.QualityDocument> docs) {
        tableModel.setRowCount(0);
        for (Model.QualityDocument doc : docs) {
            tableModel.addRow(new Object[]{
                    doc.getTitle(),
                    doc.getAuthor(),
                    doc.getCategory(),
                    doc.getDate().toString(),
                    doc.getFilePath()
            });
        }
    }

    // Getters de los combos dinámicos (opcional para el controlador)
    public JComboBox<String> getCategoryCombo() {
        return categoryCombo;
    }

    public JComboBox<String> getStockCombo() {
        return stockCombo;
    }

    public JComboBox<String> getProblemTypeCombo() {
        return problemTypeCombo;
    }

    public JComboBox<String> getStatusCombo() {
        return statusCombo;
    }

    public JComboBox<String> getPersonnelCombo() {
        return personnelCombo;
    }

    public JComboBox<String> getShiftCombo() {
        return shiftCombo;
    }
}
