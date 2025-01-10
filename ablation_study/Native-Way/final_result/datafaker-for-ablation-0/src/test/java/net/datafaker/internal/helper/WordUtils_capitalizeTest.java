
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordUtils_capitalizeTest {

    @Test
    public void testCapitalize_nullInput() {
        assertNull(WordUtils.capitalize(null));
    }

    @Test
    public void testCapitalize_emptyInput() {
        assertEquals("", WordUtils.capitalize(""));
    }

    @Test
    public void testCapitalize_alreadyCapitalized() {
        assertEquals("Hello", WordUtils.capitalize("Hello"));
    }

    @Test
    public void testCapitalize_needsCapitalization() {
        assertEquals("World", WordUtils.capitalize("world"));
    }
}
