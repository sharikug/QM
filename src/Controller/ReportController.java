package Controller;

import Model.QualityDocument;
import View.ReportView;
import dataBase.QualityDocumentRepository;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class ReportController {

    private ReportView view;
    private QualityDocumentRepository repository;

    public ReportController(ReportView view, QualityDocumentRepository repository) {
        this.view = view;
        this.repository = repository;
        initController();
    }

    private void initController() {
        view.getGenerateButton().addActionListener(e -> generateReport());
    }

    private void generateReport() {
        String reportType = (String) view.getReportTypeCombo().getSelectedItem();
        LocalDate from = view.getFromDate();
        LocalDate to = view.getToDate();

        if (from == null || to == null) {
            view.showMessage("Please select a valid date range.");
            return;
        }

        // Aquí podrías agregar condiciones específicas para filtros dinámicos por tipo
        List<QualityDocument> results = repository.getDocumentsBetweenDates(from, to);

        if (results.isEmpty()) {
            view.showMessage("No data found for the selected period.");
        }

        populateTable(results);
    }

    private void populateTable(List<QualityDocument> documents) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Title", "Author", "Category", "Date", "File Path"});

        for (QualityDocument doc : documents) {
            model.addRow(new Object[]{
                    doc.getTitle(),
                    doc.getAuthor(),
                    doc.getCategory(),
                    doc.getDate(),
                    doc.getFilePath()
            });
        }

        view.getResultTable().setModel(model);
    }
}