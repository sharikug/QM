package dataBase;

import Model.QualityDocument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DocumentRepository {

    public boolean saveDocument(QualityDocument doc) {
        String sql = "INSERT INTO quality_documents (title, author, category, date, file_path) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doc.getTitle());
            stmt.setString(2, doc.getAuthor());
            stmt.setString(3, doc.getCategory());
            stmt.setDate(4, Date.valueOf(doc.getDate())); // java.sql.Date
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
        String sql = "SELECT * FROM quality_documents WHERE date BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(from));
            stmt.setDate(2, java.sql.Date.valueOf(to));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QualityDocument doc = new QualityDocument(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("file_path")
                );
                documents.add(doc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documents;
    }
}
