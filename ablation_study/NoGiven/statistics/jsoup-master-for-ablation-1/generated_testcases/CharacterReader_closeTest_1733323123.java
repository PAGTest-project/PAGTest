
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterReader_closeTest {

    @Test
    void testCloseWithNonNullReader() throws IOException {
        CharacterReader reader = new CharacterReader(new StringReader("test"));
        Reader mockReader = mock(Reader.class);
        reader.reader = mockReader;

        reader.close();

        verify(mockReader).close();
        assertNull(reader.reader);
        assertNull(reader.charBuf);
        assertNull(reader.stringCache);
    }

    @Test
    void testCloseWithNullReader() {
        CharacterReader reader = new CharacterReader(new StringReader("test"));
        reader.reader = null;

        reader.close();

        assertNull(reader.reader);
        assertNull(reader.charBuf);
        assertNull(reader.stringCache);
    }
}
