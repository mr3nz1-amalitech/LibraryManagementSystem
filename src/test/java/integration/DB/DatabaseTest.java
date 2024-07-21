package integration.DB;

import DB.Database;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {
    private static Connection connection;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        connection = Database.getConnection();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testConnection() throws SQLException {
        Assertions.assertNotNull(connection, "Database connection should be null");
        assert (connection.isValid(2));
    }

}
