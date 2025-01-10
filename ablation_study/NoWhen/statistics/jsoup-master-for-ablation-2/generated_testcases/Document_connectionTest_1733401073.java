
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

public class Document_connectionTest {

    @Test
    public void testConnection_WithNullConnection() {
        Document doc = new Document("http://example.com");
        Connection conn = doc.connection();
        assertNotNull(conn);
    }

    @Test
    public void testConnection_WithNonNullConnection() {
        Document doc = new Document("http://example.com");
        Connection mockConn = mock(Connection.class);
        doc.connection(mockConn);
        Connection returnedConn = doc.connection();
        assertSame(mockConn, returnedConn);
    }
}
