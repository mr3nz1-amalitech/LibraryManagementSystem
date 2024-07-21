package DB;

import java.sql.*;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/library_management_system_db";
    private static String user = "root";
    private static String password = "newPassword123";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }
}
