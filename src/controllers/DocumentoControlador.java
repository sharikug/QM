package controllers;

import models.*;
import views.InterfazPrincipal;

import java.awt.event.ActionEvent;
import java.util.List;

public class DocumentoControlador {
    private final InterfazPrincipal vista;
    private final RegistroDocumentoService registroService;
    private final ReporteService reporteService;

    public DocumentoControlador(InterfazPrincipal vista) {
        this.vista = vista;
        this.registroService = new RegistroDocumentoService();
        this.reporteService = new ReporteService();

        vista.setOnRegistrar(this::registrar);
        vista.setOnFiltrarAutor(this::filtrarAutor);
        vista.setOnFiltrarCategoria(this::filtrarCategoria);
        vista.setOnMostrarTodos(this::mostrarTodos);

        mostrarTodos(null);
    }

    private void registrar(ActionEvent e) {
        String titulo = vista.getTitulo();
        String autor = vista.getAutor();
        String categoria = vista.getCategoria();
        if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
            vista.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        registroService.registrarDocumento(titulo, autor, categoria);
        vista.mostrarMensaje("Documento registrado correctamente.");
        vista.limpiarCampos();
        mostrarTodos(null);
    }

    private void filtrarAutor(ActionEvent e) {
        List<DocumentoCalidad> lista = registroService.obtenerTodos();
        vista.cargarTabla(reporteService.filtrarPorAutor(lista, vista.getFiltroAutor()));
    }

    private void filtrarCategoria(ActionEvent e) {
        List<DocumentoCalidad> lista = registroService.obtenerTodos();
        vista.cargarTabla(reporteService.filtrarPorCategoria(lista, vista.getFiltroCategoria()));
    }

    private void mostrarTodos(ActionEvent e) {
        vista.cargarTabla(registroService.obtenerTodos());
    }
}
