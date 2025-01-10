
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBufferedInput_resetTest {

    @Test
    void testResetValidMark() throws IOException {
        byte[] data = {1, 2, 3, 4, 5};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);

        bufferedInput.mark(3); // Set a valid mark
        bufferedInput.read(); // Move bufPos
        bufferedInput.reset(); // Reset to the mark

        assertEquals(2, bufferedInput.read()); // Ensure bufPos is reset correctly
    }

    @Test
    void testResetInvalidMark() {
        byte[] data = {1, 2, 3, 4, 5};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        SimpleBufferedInput bufferedInput = new SimpleBufferedInput(inputStream);

        assertThrows(IOException.class, () -> bufferedInput.reset()); // Ensure IOException is thrown
    }
}
