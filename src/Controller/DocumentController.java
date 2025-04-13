package Controller;

import Model.QualityDocument;
import view.DocumentRegistrationView;
import dataBase.DocumentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentController {

    private final DocumentRegistrationView view;
    private final List<QualityDocument> documents;
    private DocumentRepository repository = new DocumentRepository();

    public DocumentController(DocumentRegistrationView view) {
        this.view = view;
        this.documents = new ArrayList<>();
        initController();
    }

    public void setRepository(DocumentRepository repository) {
        this.repository = repository;
    }

    public void callSaveDocument() {
        saveDocument();
    }

    private void initController() {
        view.getSaveButton().addActionListener(_ -> saveDocument());

        view.getBrowseButton().addActionListener(_ -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(view instanceof Component ? (Component) view : null);
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

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "All fields except file are required.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        QualityDocument doc = new QualityDocument(title, author, category, date, filePath);
        repository.saveDocument(doc);

        JOptionPane.showMessageDialog(
                null,
                "Document saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

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
