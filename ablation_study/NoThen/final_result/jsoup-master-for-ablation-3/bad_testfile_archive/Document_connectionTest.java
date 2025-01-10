
package org.jsoup.nodes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Document_connectionTest {

    @Test
    public void testConnection_WhenConnectionIsNull() {
        Document document = new Document("http://example.com");
        Connection connection = document.connection();
        assertNotNull(connection);
    }

    @Test
    public void testConnection_WhenConnectionIsNotNull() {
        Document document = new Document("http://example.com");
        Connection mockConnection = mock(Connection.class);
        document.connection(mockConnection);
        Connection retrievedConnection = document.connection();
        assertNotNull(retrievedConnection);
    }
}
