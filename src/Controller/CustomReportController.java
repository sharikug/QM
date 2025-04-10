package Controller;

import Model.QualityDocument;
import View.CustomReportView;
import dataBase.QualityDocumentRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class CustomReportController {
    private final CustomReportView view;
    private final QualityDocumentRepository repository;

    public CustomReportController(CustomReportView view, QualityDocumentRepository repository) {
        this.view = view;
        this.repository = repository;

        view.getBtnGenerar().addActionListener(e -> generarReporte());
        view.getBtnVolver().addActionListener(e -> view.goBackToRegistration());
    }

    private void generarReporte() {
        try {
            String tipo = (String) view.getTipoReporteCombo().getSelectedItem();
            LocalDate from = view.getFromDate();
            LocalDate to = view.getToDate();

            String categoria = view.getCategoriaInsumo();
            String stock = view.getStock();
            String tipoProblema = view.getTipoProblema();
            String estado = view.getEstadoReclamo();
            String personal = view.getPersonal();
            String turno = view.getTurno();
            String autor = view.getAutor();

            List<QualityDocument> resultados = repository.getFilteredDocuments(
                    tipo, from, to,
                    categoria, stock,
                    tipoProblema, estado,
                    personal, turno, autor
            );

            view.mostrarResultados(resultados);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}