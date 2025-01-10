
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class ControllableInputStream_wrapTest {

    @Test
    void testWrapWithNonControllableInputStream() {
        // Given
        InputStream inputStream = new ByteArrayInputStream("test data".getBytes());
        int maxSize = 100;

        // When
        ControllableInputStream wrappedStream = ControllableInputStream.wrap(inputStream, maxSize);

        // Then
        assertNotNull(wrappedStream);
        assertEquals(maxSize, wrappedStream.max());
    }

    @Test
    void testWrapWithControllableInputStream() {
        // Given
        InputStream inputStream = new ControllableInputStream(new SimpleBufferedInput(new ByteArrayInputStream("test data".getBytes())), 100);
        int maxSize = 200;

        // When
        ControllableInputStream wrappedStream = ControllableInputStream.wrap(inputStream, maxSize);

        // Then
        assertSame(inputStream, wrappedStream);
    }
}
