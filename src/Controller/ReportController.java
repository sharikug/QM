package Controller;

import Model.QualityDocument;
import View.CustomReportView;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class ReportController {

    private List<QualityDocument> documents;

    public ReportController() {
        // Simulación de documentos cargados
        documents = new ArrayList<>();
        seedDocuments();
    }

    // Método que recibe filtros y retorna los resultados filtrados
    public List<QualityDocument> filterDocuments(String reportType, LocalDate from, LocalDate to, String... params) {
        return documents.stream()
                .filter(doc -> (doc.getDate().isEqual(from) || doc.getDate().isAfter(from)) &&
                        (doc.getDate().isEqual(to) || doc.getDate().isBefore(to)))
                .collect(Collectors.toList()); // Aquí se podrían agregar más condiciones por tipo de reporte
    }

    // Simulación de documentos cargados
    private void seedDocuments() {
        documents.add(new QualityDocument("Manual de procesos", "Ana", "Normativo", LocalDate.of(2024, 12, 10), ""));
        documents.add(new QualityDocument("Reporte de reclamos", "Carlos", "Reclamos", LocalDate.of(2025, 1, 15), ""));
        documents.add(new QualityDocument("Inventario Q1", "Lucía", "Inventario", LocalDate.of(2025, 3, 5), ""));
    }

    public List<QualityDocument> getDocuments() {
        return documents;
    }
}