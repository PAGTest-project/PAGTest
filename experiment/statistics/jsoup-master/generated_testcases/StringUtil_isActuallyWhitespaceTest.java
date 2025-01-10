
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isActuallyWhitespaceTest {

    @Test
    void testIsActuallyWhitespace() {
        assertTrue(StringUtil.isActuallyWhitespace(' '));
        assertTrue(StringUtil.isActuallyWhitespace('\t'));
        assertTrue(StringUtil.isActuallyWhitespace('\n'));
        assertTrue(StringUtil.isActuallyWhitespace('\f'));
        assertTrue(StringUtil.isActuallyWhitespace('\r'));
        assertTrue(StringUtil.isActuallyWhitespace(160));

        assertFalse(StringUtil.isActuallyWhitespace('a'));
        assertFalse(StringUtil.isActuallyWhitespace('1'));
        assertFalse(StringUtil.isActuallyWhitespace(127));
    }
}
