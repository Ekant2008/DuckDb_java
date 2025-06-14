import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class ClustertableTest {
    private Connection conn;

    @BeforeEach
    public void setUp() throws SQLException {
        conn = Clustertable.createCluster();
        Clustertable.insertSampleData(conn);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    public void testInsertedClusterData() throws SQLException, SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ClusterTable");

        assertTrue(rs.next());

        assertEquals("Chhindwara", rs.getString("ClusterName"));
        assertEquals("Y", rs.getString("ACTIVE"));
        assertEquals("org101", rs.getString("Org_ID"));
        assertEquals(Timestamp.valueOf("2025-06-14 10:00:00"), rs.getTimestamp("CreateTime"));

        assertFalse(rs.next()); // only one row should exist

        rs.close();
        stmt.close();
    }

}