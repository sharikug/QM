package models;

public class DocumentoCalidad {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private String fecha;

    public DocumentoCalidad(int id, String titulo, String autor, String categoria, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public String getFecha() { return fecha; }
}
