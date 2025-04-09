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
    private JButton browseButton;
    private JTextField filePathField;
    private JButton saveButton;

    public DocumentRegistrationView() {
        setTitle("Register Quality Document");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Title
        add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        titleField = new JTextField(20);
        add(titleField, gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        authorField = new JTextField(20);
        add(authorField, gbc);

        // Category
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        categoryCombo = new JComboBox<>(new String[]{"Procedimiento", "Manual", "Reglamento", "Política"});
        add(categoryCombo, gbc);

        // Date
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        add(dateSpinner, gbc);

        // File chooser
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("File Path:"), gbc);
        gbc.gridx = 1;
        filePathField = new JTextField(20);
        filePathField.setEditable(false);
        add(filePathField, gbc);

        gbc.gridx = 2;
        browseButton = new JButton("Browse...");
        add(browseButton, gbc);

        // Save button
        gbc.gridx = 1;
        gbc.gridy++;
        saveButton = new JButton("Save Document");
        add(saveButton, gbc);

        browseButton.addActionListener(e -> chooseFile());

        setVisible(true);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    // === Getters para el controlador ===
    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
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

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void clearForm() {
        titleField.setText("");
        authorField.setText("");
        filePathField.setText("");
        categoryCombo.setSelectedIndex(0);
        dateSpinner.setValue(new Date());
    }
}
