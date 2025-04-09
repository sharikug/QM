package view;

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
    private JButton browseButton;
    private JButton saveButton;

    public DocumentRegistrationView() {
        setTitle("Register Quality Document");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Campos del formulario
        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel("Category:"));
        categoryCombo = new JComboBox<>(new String[] {"Normativo", "Inventario", "Reclamos", "Otros"});
        add(categoryCombo);

        add(new JLabel("Date:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        add(dateSpinner);

        add(new JLabel("File:"));
        JPanel filePanel = new JPanel(new BorderLayout());
        filePathField = new JTextField();
        browseButton = new JButton("Browse");
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.EAST);
        add(filePanel);

        // Botón de guardar
        saveButton = new JButton("Save");
        add(saveButton);

        // Relleno
        add(new JLabel());

        setVisible(true);
    }

    public String getTitleInput() {
        return titleField.getText();
    }

    public String getAuthorInput() {
        return authorField.getText();
    }

    public String getCategoryInput() {
        return categoryCombo.getSelectedItem().toString();
    }

    public LocalDate getDateInput() {
        Date selectedDate = (Date) dateSpinner.getValue();
        return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getFilePath() {
        return filePathField.getText();
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public JComboBox<String> getCategoryCombo() {
        return categoryCombo;
    }

    public JTextField getFilePathField() {
        return filePathField;
    }

}
