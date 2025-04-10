package Controller;

import Model.QualityDocument;
import View.CustomReportView;
import View.DocumentRegistrationView;
import View.ReportView;
import dataBase.QualityDocumentRepository;

import java.time.LocalDate;

public class DocumentController {

    private DocumentRegistrationView view;
    private QualityDocumentRepository repository;

    public DocumentController(DocumentRegistrationView view, QualityDocumentRepository repository) {
        this.view = view;
        this.repository = repository;
        initController();
    }

    private void initController() {
        view.getSaveButton().addActionListener(e -> registerDocument());
        view.getReportButton().addActionListener(e -> openReportView());
        view.getCustomReportButton().addActionListener(e -> openCustomReportView());
    }

    private void registerDocument() {
        String title = view.getTitleField();
        String author = view.getAuthorField();
        String category = (String) view.getCategoryCombo().getSelectedItem();
        LocalDate date = view.getSelectedDate();
        String filePath = view.getFilePath();

        if (title.isEmpty() || author.isEmpty() || category == null || date == null || filePath.isEmpty()) {
            view.showMessage("All fields are required.");
            return;
        }

        QualityDocument doc = new QualityDocument(title, author, category, date, filePath);
        boolean success = repository.saveDocument(doc);

        if (success) {
            view.showMessage("Document registered successfully.");
        } else {
            view.showMessage("Error registering document.");
        }
    }

    private void openReportView() {
        ReportView reportView = new ReportView();
        reportView.setVisible(true);
    }

    private void openCustomReportView() {
        CustomReportView customReportView = new CustomReportView();
        customReportView.setVisible(true);
    }
}
