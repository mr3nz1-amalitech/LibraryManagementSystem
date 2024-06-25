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

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

}
