package Controller;

import Model.QualityDocument;
import view.DocumentRegistrationView;
import dataBase.DocumentRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DocumentController {

    private DocumentRegistrationView view;
    private List<QualityDocument> documents;
    private DocumentRepository repository = new DocumentRepository();

    public DocumentController(DocumentRegistrationView view) {
        this.view = view;
        this.documents = new ArrayList<>();

        initController();
    }

    private void initController() {
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDocument();
            }
        });

        view.getBrowseButton().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(view);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                view.getFilePathField().setText(filePath);
            }
        });
    }

    private void saveDocument() {
        String title = view.getTitleInput();
        String author = view.getAuthorInput();
        String category = view.getCategoryInput();
        String filePath = view.getFilePath();
        var date = view.getDateInput();

        // Validaciones básicas
        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields except file are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        QualityDocument doc = new QualityDocument(title, author, category, date, filePath);
        repository.saveDocument(doc);

        JOptionPane.showMessageDialog(view, "Document saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

    private void clearForm() {
        view.getTitleField().setText("");
        view.getAuthorField().setText("");
        view.getCategoryCombo().setSelectedIndex(0);
        view.getFilePathField().setText("");
    }

    public List<QualityDocument> getDocuments() {
        return documents;
    }
}
