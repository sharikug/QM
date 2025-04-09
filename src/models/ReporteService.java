package models;

import java.util.*;
import java.util.stream.Collectors;

public class ReporteService {

    public List<DocumentoCalidad> filtrarPorAutor(List<DocumentoCalidad> documentos, String autor) {
        return documentos.stream()
                .filter(d -> d.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<DocumentoCalidad> filtrarPorCategoria(List<DocumentoCalidad> documentos, String categoria) {
        return documentos.stream()
                .filter(d -> d.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    // Aquí luego añadiremos más lógica para otros tipos de reporte
}
