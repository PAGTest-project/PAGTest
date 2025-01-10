
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Document_connectionTest {

    @Test
    public void testConnection_WhenConnectionIsNull() {
        Document doc = new Document("http://example.com");
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.newRequest()).thenReturn(mockConnection);

        Connection result = doc.connection();
        assertNotNull(result);
    }

    @Test
    public void testConnection_WhenConnectionIsNotNull() {
        Document doc = new Document("http://example.com");
        Connection mockConnection = mock(Connection.class);
        doc.connection(mockConnection);

        Connection result = doc.connection();
        assertSame(mockConnection, result);
    }
}
