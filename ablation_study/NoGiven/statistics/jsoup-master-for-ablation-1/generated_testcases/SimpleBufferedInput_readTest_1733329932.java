
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SimpleBufferedInput_readTest {

    @Test
    void testRead_BufferNotEmpty() throws IOException {
        InputStream mockInputStream = Mockito.mock(InputStream.class);
        when(mockInputStream.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(5);

        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(mockInputStream);
        bufferedInput.fill(); // Ensure buffer is filled

        int result = bufferedInput.read();
        assertEquals(0, result); // Assuming the first byte in the buffer is 0
    }

    @Test
    void testRead_BufferEmptyAndStreamEmpty() throws IOException {
        InputStream mockInputStream = Mockito.mock(InputStream.class);
        when(mockInputStream.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(-1);

        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(mockInputStream);
        bufferedInput.fill(); // Ensure buffer is filled

        int result = bufferedInput.read();
        assertEquals(-1, result); // Stream is empty, should return -1
    }

    @Test
    void testRead_BufferEmptyAndStreamHasData() throws IOException {
        byte[] data = {1, 2, 3, 4, 5};
        InputStream inputStream = new ByteArrayInputStream(data);

        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);
        bufferedInput.fill(); // Ensure buffer is filled

        int result = bufferedInput.read();
        assertEquals(1, result); // First byte from the stream
    }

    @Test
    void testRead_IOExceptionDuringFill() throws IOException {
        InputStream mockInputStream = Mockito.mock(InputStream.class);
        when(mockInputStream.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt())).thenThrow(new IOException("Test Exception"));

        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(mockInputStream);

        assertThrows(IOException.class, () -> bufferedInput.read());
    }
}
