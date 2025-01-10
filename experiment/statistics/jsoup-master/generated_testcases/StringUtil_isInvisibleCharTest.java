
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isInvisibleCharTest {

    @Test
    void testIsInvisibleChar() {
        assertTrue(StringUtil.isInvisibleChar(8203)); // zero width space
        assertTrue(StringUtil.isInvisibleChar(173));  // soft hyphen
        assertFalse(StringUtil.isInvisibleChar(32));  // space
        assertFalse(StringUtil.isInvisibleChar(9));   // tab
    }
}
