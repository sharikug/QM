package models;

import java.sql.*;
import java.util.*;

public class RegistroDocumentoService {

    public void registrarDocumento(String titulo, String autor, String categoria) {
        String sql = "INSERT INTO documento_calidad (titulo, autor, categoria, fecha) VALUES (?, ?, ?, NOW())";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setString(3, categoria);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<DocumentoCalidad> obtenerTodos() {
        List<DocumentoCalidad> lista = new ArrayList<>();
        String sql = "SELECT * FROM documento_calidad ORDER BY fecha DESC";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new DocumentoCalidad(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getString("fecha")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
