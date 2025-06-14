import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Usertable {

    public static Connection createInMemoryDb() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:");
        try (Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS UserTable (USER_ID INTEGER," +
                    "NAME VARCHAR," +
                    "ACTIVE VARCHAR CHECK (ACTIVE IN ('Y', 'N'))," +
                    "CREATE_DATE DATE," +
                    "ORDER_ID VARCHAR)");
        }
        return conn;
    }

    public static void insertSampleData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO UserTable VALUES (101, 'Aman', 'Y', '2025-01-01', 'or01')");
            }
    }
}
