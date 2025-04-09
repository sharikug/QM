package Controller;

import Model.QualityDocument;
import View.CustomReportView;
import dataBase.DocumentRepository;

import java.time.LocalDate;
import java.util.List;

public class ReportController {

    private CustomReportView view;
    private DocumentRepository repository = new DocumentRepository();

    public ReportController(CustomReportView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getGenerateButton().addActionListener(e -> generateReport());
    }

    private void generateReport() {
        String reportType = (String) view.getReportTypeCombo().getSelectedItem();
        LocalDate from = view.getDateFrom();
        LocalDate to = view.getDateTo();

        List<QualityDocument> results = repository.getDocumentsBetweenDates(from, to);
        view.updateTable(results);
    }
}