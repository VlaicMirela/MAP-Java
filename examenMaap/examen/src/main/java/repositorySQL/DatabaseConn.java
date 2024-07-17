package repositorySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {

    private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\vlaic\\examenMaap\\examen\\songs.sqlite";
    private Connection conn;


    public void connect() throws SQLException {
        conn = DriverManager.getConnection(DATABASE_URL);
    }

    public Connection getConn() {
        return conn;
    }
}