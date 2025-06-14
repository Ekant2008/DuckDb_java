

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Organization {

    public static Connection createOrganization() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:");
        Statement stmt = conn.createStatement();
        stmt.execute("Create Table IF NOT EXISTS OrganizationTable(Org_ID VARCHAR," +
                "NAME VARCHAR," +
                "ACTIVE VARCHAR CHECK (ACTIVE IN ('Y', 'N'))," +
                "CreateTime TIMESTAMP)");

        return conn;
    }
    public static void insertSampleData(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO OrganizationTable VALUES ('org101', 'Aman', 'Y', '2025-06-14 10:00:00')");
        }
    }
}
