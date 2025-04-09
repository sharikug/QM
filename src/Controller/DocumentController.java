package Controller;

import Model.QualityDocument;
import View.DocumentRegistrationView;
import dataBase.DocumentRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DocumentController {

    private DocumentRegistrationView view;
    private DocumentRepository repository;

    public DocumentController(DocumentRegistrationView view) {
        this.view = view;
        this.repository = new DocumentRepository();
        initController();
    }

    private void initController() {
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerDocument();
            }
        });
    }

    private void registerDocument() {
        String title = view.getTitleField().getText();
        String author = view.getAuthorField().getText();
        String category = (String) view.getCategoryCombo().getSelectedItem();
        LocalDate date = view.getSelectedDate();
        String filePath = view.getFilePath(); // podrías obtener esto desde un file chooser

        if (title.isEmpty() || author.isEmpty() || category == null || filePath == null) {
            view.showMessage("All fields are required.");
            return;
        }

        QualityDocument doc = new QualityDocument(title, author, category, date, filePath);
        boolean success = repository.saveDocument(doc);

        if (success) {
            view.showMessage("Document saved successfully!");
            view.clearForm();
        } else {
            view.showMessage("Error saving document.");
        }
    }
}
