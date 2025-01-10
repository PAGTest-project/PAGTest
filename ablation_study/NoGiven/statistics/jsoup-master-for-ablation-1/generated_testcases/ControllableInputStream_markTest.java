
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class ControllableInputStream_markTest {

    @Test
    void testMarkAndReset() throws Exception {
        byte[] data = "test data".getBytes();
        InputStream inputStream = new ByteArrayInputStream(data);
        ControllableInputStream cis = ControllableInputStream.wrap(inputStream, data.length);

        cis.mark(data.length);
        assertEquals(data.length, cis.markPos);

        cis.read(new byte[data.length], 0, data.length);
        assertEquals(data.length, cis.readPos);

        cis.reset();
        assertEquals(0, cis.readPos);
        assertEquals(data.length, cis.remaining);
    }
}
