package modelo;

import java.sql.*;
import java.util.Properties;

/**
 * Clase BD que administra la base de datos a la que nos conectaremos para la persistencia de datos
 * @author Alberto
 *
 */
public class BdConnection{
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/organizadormiembros";
private static Connection instance = null;
private BdConnection() {
}
/**
 * Singletone que inicializa la conexión a la base de datos y nos permite usar los emtodos aun sin instanciar un objeto.
 * @return
 * @throws SQLException
 */
public static Connection getConnection() throws SQLException {
    if (instance == null) {
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "");
        instance = DriverManager.getConnection(JDBC_URL, props);

    }
    return instance;
}

}