package views;

import models.DocumentoCalidad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {
    private JTextField txtTitulo, txtAutor, txtCategoria;
    private JButton btnRegistrar, btnFiltrarAutor, btnFiltrarCategoria, btnMostrar;
    private JTable tabla;
    private DefaultTableModel tablaModel;
    private JTextField txtFiltroAutor, txtFiltroCategoria;

    public InterfazPrincipal() {
        setTitle("Gestión de Documentos de Calidad");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtCategoria = new JTextField();
        btnRegistrar = new JButton("Registrar");

        panelFormulario.add(new JLabel("Título:")); panelFormulario.add(txtTitulo);
        panelFormulario.add(new JLabel("Autor:")); panelFormulario.add(txtAutor);
        panelFormulario.add(new JLabel("Categoría:")); panelFormulario.add(txtCategoria);
        panelFormulario.add(btnRegistrar);

        add(panelFormulario, BorderLayout.NORTH);

        tablaModel = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "Categoría", "Fecha"}, 0);
        tabla = new JTable(tablaModel);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelFiltros = new JPanel(new GridLayout(2, 3));
        txtFiltroAutor = new JTextField();
        txtFiltroCategoria = new JTextField();
        btnFiltrarAutor = new JButton("Filtrar por Autor");
        btnFiltrarCategoria = new JButton("Filtrar por Categoría");
        btnMostrar = new JButton("Mostrar Todos");

        panelFiltros.add(new JLabel("Autor:")); panelFiltros.add(txtFiltroAutor); panelFiltros.add(btnFiltrarAutor);
        panelFiltros.add(new JLabel("Categoría:")); panelFiltros.add(txtFiltroCategoria); panelFiltros.add(btnFiltrarCategoria);

        add(panelFiltros, BorderLayout.SOUTH);
        add(btnMostrar, BorderLayout.WEST);

        setVisible(true);
    }

    // Métodos públicos para el controlador

    public String getTitulo() { return txtTitulo.getText(); }
    public String getAutor() { return txtAutor.getText(); }
    public String getCategoria() { return txtCategoria.getText(); }

    public String getFiltroAutor() { return txtFiltroAutor.getText(); }
    public String getFiltroCategoria() { return txtFiltroCategoria.getText(); }

    public void limpiarCampos() {
        txtTitulo.setText(""); txtAutor.setText(""); txtCategoria.setText("");
    }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void cargarTabla(List<DocumentoCalidad> documentos) {
        tablaModel.setRowCount(0);
        for (DocumentoCalidad doc : documentos) {
            tablaModel.addRow(new Object[]{doc.getId(), doc.getTitulo(), doc.getAutor(), doc.getCategoria(), doc.getFecha()});
        }
    }

    public void setOnRegistrar(ActionListener al) { btnRegistrar.addActionListener(al); }
    public void setOnFiltrarAutor(ActionListener al) { btnFiltrarAutor.addActionListener(al); }
    public void setOnFiltrarCategoria(ActionListener al) { btnFiltrarCategoria.addActionListener(al); }
    public void setOnMostrarTodos(ActionListener al) { btnMostrar.addActionListener(al); }
}
