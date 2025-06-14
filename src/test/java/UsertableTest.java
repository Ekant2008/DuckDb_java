import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsertableTest {

    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = Usertable.createInMemoryDb();
        Usertable.insertSampleData(conn);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    void testActiveStatus() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NAME FROM UserTable WHERE ACTIVE = 'Y'");

        assertTrue(rs.next());
        assertEquals("Aman", rs.getString("NAME"));

        rs.close();
        stmt.close();
    }
}
