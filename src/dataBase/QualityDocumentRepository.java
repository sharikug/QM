package dataBase;

import Model.QualityDocument;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QualityDocumentRepository {
    private final String url = "jdbc:mysql://localhost:3306/quality_documents";
    private final String user = "root";
    private final String password = "";

    public boolean saveDocument(QualityDocument doc) {
        String sql = "INSERT INTO documents (title, author, category, date, file_path) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doc.getTitle());
            stmt.setString(2, doc.getAuthor());
            stmt.setString(3, doc.getCategory());
            stmt.setDate(4, Date.valueOf(doc.getDate()));
            stmt.setString(5, doc.getFilePath());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<QualityDocument> getDocumentsBetweenDates(LocalDate from, LocalDate to) {
        List<QualityDocument> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE date BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(from));
            stmt.setDate(2, Date.valueOf(to));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                documents.add(mapRowToDocument(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public List<QualityDocument> getFilteredDocuments(String reportType, LocalDate from, LocalDate to,
                                                      String categoria, String stock,
                                                      String tipoProblema, String estado,
                                                      String personal, String turno, String autor) {
        List<QualityDocument> documents = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM documents WHERE date BETWEEN ? AND ?");

        if (categoria != null && !categoria.isEmpty()) sql.append(" AND category = ?");
        if (autor != null && !autor.isEmpty()) sql.append(" AND author LIKE ?");
        // Puedes agregar más filtros condicionales según tus necesidades

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            stmt.setDate(1, Date.valueOf(from));
            stmt.setDate(2, Date.valueOf(to));

            int index = 3;
            if (categoria != null && !categoria.isEmpty()) stmt.setString(index++, categoria);
            if (autor != null && !autor.isEmpty()) stmt.setString(index++, "%" + autor + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                documents.add(mapRowToDocument(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    private QualityDocument mapRowToDocument(ResultSet rs) throws SQLException {
        return new QualityDocument(
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getDate("date").toLocalDate(),
                rs.getString("file_path")
        );
    }
}
