package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO {
protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;

    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "vixlia2024";
    private final String DATABASE = "db_vivero";

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String ZONA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    protected void connectarDataBase() {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + ZONA;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void desconectarDataBase() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
} 