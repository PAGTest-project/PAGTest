
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllableInputStream_readToByteBufferTest {

    @Test
    public void testReadToByteBuffer_Capped() throws IOException {
        byte[] data = "test data".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteBuffer result = ControllableInputStream.readToByteBuffer(inputStream, 4);
        assertEquals(4, result.limit());
        assertEquals("test", new String(result.array(), 0, result.limit()));
    }

    @Test
    public void testReadToByteBuffer_Uncapped() throws IOException {
        byte[] data = "test data".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteBuffer result = ControllableInputStream.readToByteBuffer(inputStream, 0);
        assertEquals(data.length, result.limit());
        assertEquals("test data", new String(result.array(), 0, result.limit()));
    }

    @Test
    public void testReadToByteBuffer_NullInputStream() {
        assertThrows(IllegalArgumentException.class, () -> {
            ControllableInputStream.readToByteBuffer(null, 10);
        });
    }

    @Test
    public void testReadToByteBuffer_NegativeMaxSize() {
        byte[] data = "test data".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        assertThrows(IllegalArgumentException.class, () -> {
            ControllableInputStream.readToByteBuffer(inputStream, -1);
        });
    }
}
