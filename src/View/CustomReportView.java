package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CustomReportView extends JFrame {

    private JComboBox<String> reportTypeCombo;
    private JSpinner dateFromSpinner;
    private JSpinner dateToSpinner;

    // Panel dinámico para los filtros específicos
    private JPanel dynamicFiltersPanel;

    public CustomReportView() {
        setTitle("Custom Report Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Tipo de reporte
        JLabel reportTypeLabel = new JLabel("Report Type:");
        reportTypeCombo = new JComboBox<>(new String[] {
                "Ventas",
                "Inventario",
                "Productos más vendidos",
                "Desempeño del personal",
                "Reporte de reclamos/problemas",
                "Reporte personalizado"
        });

        // Fecha desde
        JLabel fromLabel = new JLabel("From:");
        dateFromSpinner = new JSpinner(new SpinnerDateModel());
        dateFromSpinner.setEditor(new JSpinner.DateEditor(dateFromSpinner, "yyyy-MM-dd"));

        // Fecha hasta
        JLabel toLabel = new JLabel("To:");
        dateToSpinner = new JSpinner(new SpinnerDateModel());
        dateToSpinner.setEditor(new JSpinner.DateEditor(dateToSpinner, "yyyy-MM-dd"));

        // Agregar componentes
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(reportTypeLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(reportTypeCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(fromLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(dateFromSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(toLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(dateToSpinner, gbc);

        // Panel para los filtros dinámicos
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        dynamicFiltersPanel = new JPanel();
        dynamicFiltersPanel.setLayout(new BoxLayout(dynamicFiltersPanel, BoxLayout.Y_AXIS));
        dynamicFiltersPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        mainPanel.add(dynamicFiltersPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Listeners para actualizar los filtros dinámicos
        reportTypeCombo.addActionListener(e -> updateDynamicFilters());

        setVisible(true);
    }

    private void updateDynamicFilters() {
        dynamicFiltersPanel.removeAll();
        String selected = (String) reportTypeCombo.getSelectedItem();

        switch (selected) {
            case "Inventario":
                dynamicFiltersPanel.add(new JLabel("Category:"));
                dynamicFiltersPanel.add(new JTextField(15));

                dynamicFiltersPanel.add(new JLabel("Stock:"));
                dynamicFiltersPanel.add(new JTextField(15));
                break;

            case "Reporte de reclamos/problemas":
                dynamicFiltersPanel.add(new JLabel("Problem Type:"));
                dynamicFiltersPanel.add(new JTextField(15));

                dynamicFiltersPanel.add(new JLabel("Status:"));
                dynamicFiltersPanel.add(new JComboBox<>(new String[] {"Resuelto", "Pendiente"}));
                break;

            case "Desempeño del personal":
                dynamicFiltersPanel.add(new JLabel("Staff:"));
                dynamicFiltersPanel.add(new JComboBox<>(new String[] {"Empleado 1", "Empleado 2"})); // Temporal

                dynamicFiltersPanel.add(new JLabel("Shift:"));
                dynamicFiltersPanel.add(new JComboBox<>(new String[] {"Mañana", "Tarde", "Noche"}));
                break;

            default:
                // No filtros extra para otros tipos
                dynamicFiltersPanel.add(new JLabel("No additional filters."));
                break;
        }

        dynamicFiltersPanel.revalidate();
        dynamicFiltersPanel.repaint();
    }

    public static void main(String[] args) {
        new CustomReportView();
    }
}
