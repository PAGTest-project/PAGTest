
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ControllableInputStream_resetTest {

    @Test
    void testReset() throws IOException {
        // Given
        byte[] data = "test data".getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ControllableInputStream cis = ControllableInputStream.wrap(bais, data.length);
        cis.mark(data.length);
        cis.read(new byte[data.length], 0, data.length);

        // When
        cis.reset();

        // Then
        assertEquals(data.length, cis.max() - cis.remaining());
        assertEquals(0, cis.readPos);
    }
}
