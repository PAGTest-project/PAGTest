
package org.jsoup.helper;

import org.jsoup.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HttpConnection_newRequestTest {

    @Test
    public void testNewRequest() {
        // Given
        HttpConnection httpConnection = new HttpConnection();

        // When
        Connection newRequest = httpConnection.newRequest();

        // Then
        assertNotNull(newRequest);
    }
}
