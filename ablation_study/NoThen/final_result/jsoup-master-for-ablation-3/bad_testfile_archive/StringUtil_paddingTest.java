
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_paddingTest {

    @Test
    public void testPaddingWithValidWidth() {
        assertEquals("   ", StringUtil.padding(3, 30));
        assertEquals("                    ", StringUtil.padding(20, 30));
    }

    @Test
    public void testPaddingWithMaxPaddingWidth() {
        assertEquals("          ", StringUtil.padding(10, 10));
        assertEquals("                    ", StringUtil.padding(20, 20));
    }

    @Test
    public void testPaddingWithUnlimitedWidth() {
        assertEquals("                              ", StringUtil.padding(30, -1));
        assertEquals("                                   ", StringUtil.padding(31, -1));
    }

    @Test
    public void testPaddingWithZeroWidth() {
        assertEquals("", StringUtil.padding(0, 30));
    }

    @Test
    public void testPaddingWithNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.padding(-1, 30));
    }

    @Test
    public void testPaddingWithNegativeMaxPaddingWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.padding(10, -2));
    }
}
