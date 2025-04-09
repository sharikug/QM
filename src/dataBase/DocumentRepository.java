package dataBase;

import Model.QualityDocument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentRepository {

    public void saveDocument(QualityDocument doc) {
        String sql = "INSERT INTO quality_documents (title, author, category, date, file_path) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doc.getTitle());
            stmt.setString(2, doc.getAuthor());
            stmt.setString(3, doc.getCategory());
            stmt.setDate(4, java.sql.Date.valueOf(doc.getDate()));
            stmt.setString(5, doc.getFilePath());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Puedes mostrar esto con JOptionPane también
        }
    }
}
