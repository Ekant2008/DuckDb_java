import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Clustertable {


    public static Connection createCluster() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS ClusterTable (" +
                "ClusterName VARCHAR, " +
                "ACTIVE VARCHAR CHECK (ACTIVE IN ('Y', 'N')), " +
                "CreateTime TIMESTAMP, " +
                "Org_ID VARCHAR)");
        return conn;
    }
    public static void insertSampleData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO ClusterTable VALUES ('Chhindwara','Y', '2025-06-14 10:00:00','org101')");
        }
    }
}
