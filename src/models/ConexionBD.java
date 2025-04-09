package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/ingsii";
    private static final String USER = "root";
    private static final String PASS = ""; // ajusta tu contraseña

    public static Connection conectar() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
