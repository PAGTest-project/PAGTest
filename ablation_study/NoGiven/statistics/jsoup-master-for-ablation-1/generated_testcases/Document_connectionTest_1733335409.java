
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class Document_connectionTest {

    @Test
    public void testConnection_WhenConnectionIsNull() {
        Document doc = new Document("http://example.com");
        Connection conn = doc.connection();
        assertNotNull(conn);
    }

    @Test
    public void testConnection_WhenConnectionIsNotNull() {
        Document doc = new Document("http://example.com");
        Connection mockConnection = Jsoup.newSession();
        doc.connection = mockConnection;
        Connection conn = doc.connection();
        assertSame(mockConnection, conn);
    }
}
