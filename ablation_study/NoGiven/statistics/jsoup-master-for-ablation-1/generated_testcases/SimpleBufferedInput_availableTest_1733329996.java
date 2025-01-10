
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBufferedInput_availableTest {

    @Test
    void testAvailableWithBufferedData() throws IOException {
        byte[] data = new byte[10];
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);

        // Fill the buffer
        bufferedInput.read();

        // Test available() with buffered data
        assertEquals(9, bufferedInput.available());
    }

    @Test
    void testAvailableWithNoBufferedDataAndInputStreamAvailable() throws IOException {
        byte[] data = new byte[10];
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);

        // Test available() with no buffered data but input stream available
        assertEquals(10, bufferedInput.available());
    }

    @Test
    void testAvailableWithNoBufferedDataAndInputStreamFullyRead() throws IOException {
        byte[] data = new byte[0];
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);

        // Read all data to mark the input stream as fully read
        bufferedInput.read();

        // Test available() with no buffered data and input stream fully read
        assertEquals(0, bufferedInput.available());
    }
}
