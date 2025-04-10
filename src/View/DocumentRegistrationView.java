package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DocumentRegistrationView extends JFrame {

    private JTextField titleField;
    private JTextField authorField;
    private JComboBox<String> categoryCombo;
    private JSpinner dateSpinner;
    private JTextField filePathField;
    private JButton uploadButton;
    private JButton saveButton;
    private JButton reportButton;
    private JButton customReportButton;

    public DocumentRegistrationView() {
        setTitle("Registro de Documentos de Calidad");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(8, 2, 10, 10));

        JLabel titleLabel = new JLabel("Título:");
        titleField = new JTextField();

        JLabel authorLabel = new JLabel("Autor:");
        authorField = new JTextField();

        JLabel categoryLabel = new JLabel("Categoría:");
        categoryCombo = new JComboBox<>(new String[]{"Procedimiento", "Instructivo", "Manual", "Otro"});

        JLabel dateLabel = new JLabel("Fecha:");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        JLabel fileLabel = new JLabel("Archivo:");
        filePathField = new JTextField();
        filePathField.setEditable(false);
        uploadButton = new JButton("Subir Archivo");
        uploadButton.addActionListener(e -> openFileChooser());

        saveButton = new JButton("Guardar Documento");
        reportButton = new JButton("Ver Reportes");
        customReportButton = new JButton("Reporte Personalizado");

        // Agregar componentes
        add(titleLabel);
        add(titleField);

        add(authorLabel);
        add(authorField);

        add(categoryLabel);
        add(categoryCombo);

        add(dateLabel);
        add(dateSpinner);

        add(fileLabel);
        add(filePathField);
        add(new JLabel()); // espacio vacío
        add(uploadButton);

        add(saveButton);
        add(reportButton);
        add(new JLabel()); // espacio vacío
        add(customReportButton);
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    // Métodos de acceso (getters)
    public String getTitleField() {
        return titleField.getText();
    }

    public String getAuthorField() {
        return authorField.getText();
    }

    public JComboBox<String> getCategoryCombo() {
        return categoryCombo;
    }

    public LocalDate getSelectedDate() {
        Date date = (Date) dateSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getFilePath() {
        return filePathField.getText();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public JButton getCustomReportButton() {
        return customReportButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
