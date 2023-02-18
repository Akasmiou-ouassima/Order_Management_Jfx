package ma.enset.tpjdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexionDb {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gestion_commandes", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}


