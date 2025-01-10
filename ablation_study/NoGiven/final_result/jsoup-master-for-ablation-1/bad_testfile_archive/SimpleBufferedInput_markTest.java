
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBufferedInput_markTest {

    @Test
    void testMarkWithValidReadLimit() throws IOException {
        SimpleBufferedInput input = new SimpleBufferedInput(new ByteArrayInputStream(new byte[SimpleBufferedInput.BufferSize]));
        input.mark(SimpleBufferedInput.BufferSize - 1);
        assertEquals(0, input.bufMark);
    }

    @Test
    void testMarkWithInvalidReadLimit() {
        SimpleBufferedInput input = new SimpleBufferedInput(new ByteArrayInputStream(new byte[SimpleBufferedInput.BufferSize]));
        assertThrows(IllegalArgumentException.class, () -> input.mark(SimpleBufferedInput.BufferSize + 1));
    }
}
