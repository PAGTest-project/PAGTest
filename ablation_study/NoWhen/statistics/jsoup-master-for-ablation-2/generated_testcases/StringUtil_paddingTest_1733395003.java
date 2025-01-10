
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_paddingTest {

    @Test
    public void testPaddingWithValidWidth() {
        assertEquals("   ", StringUtil.padding(3, -1));
        assertEquals("                    ", StringUtil.padding(20, -1));
    }

    @Test
    public void testPaddingWithMaxPaddingWidth() {
        assertEquals("          ", StringUtil.padding(10, 10));
        assertEquals("                    ", StringUtil.padding(25, 20));
    }

    @Test
    public void testPaddingWithUnlimitedMaxPaddingWidth() {
        assertEquals("                              ", StringUtil.padding(30, -1));
        assertEquals("                                   ", StringUtil.padding(31, -1));
    }

    @Test
    public void testPaddingWithZeroWidth() {
        assertEquals("", StringUtil.padding(0, -1));
        assertEquals("", StringUtil.padding(0, 10));
    }

    @Test
    public void testPaddingWithNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.padding(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> StringUtil.padding(-1, 10));
    }

    @Test
    public void testPaddingWithNegativeMaxPaddingWidth() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.padding(10, -2));
    }
}
