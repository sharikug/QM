package Model;

import java.time.LocalDate;

public class QualityDocument {

    private String title;
    private String author;
    private String category;
    private LocalDate date;
    private String filePath;

    public QualityDocument(String title, String author, String category, LocalDate date, String filePath) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.date = date;
        this.filePath = filePath;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFilePath() {
        return filePath;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Para mostrar en tablas o logs
    @Override
    public String toString() {
        return "QualityDocument{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
