
package org.jsoup.helper;

import org.jsoup.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HttpConnection_connectTest {

    @Test
    public void testConnect() {
        // Given
        String url = "http://example.com";

        // When
        Connection con = HttpConnection.connect(url);

        // Then
        assertNotNull(con);
    }
}
