import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {

    private static Connection conn;

    @BeforeAll
    public static void setUp() throws SQLException {
        conn = Organization.createOrganization();
        Organization.insertSampleData(conn);
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    public void testInsertedData() throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM OrganizationTable WHERE Org_ID = 'org101'")) {

            assertTrue(rs.next(), "No data found for org101");

            assertEquals("org101", rs.getString("Org_ID"));
            assertEquals("Aman", rs.getString("NAME"));
            assertEquals("Y", rs.getString("ACTIVE"));
            assertEquals(Timestamp.valueOf("2025-06-14 10:00:00"), rs.getTimestamp("CreateTime"));
        }
    }
}
