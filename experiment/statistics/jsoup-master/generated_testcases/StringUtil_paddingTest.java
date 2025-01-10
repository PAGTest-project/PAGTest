
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_paddingTest {

    @Test
    public void testPaddingWithMemoizedValue() {
        assertEquals("   ", StringUtil.padding(3, 20));
    }

    @Test
    public void testPaddingWithCustomWidth() {
        assertEquals("     ", StringUtil.padding(5, 20));
    }

    @Test
    public void testPaddingWithMaxPaddingWidth() {
        assertEquals("                    ", StringUtil.padding(30, 20));
    }

    @Test
    public void testPaddingWithUnlimitedWidth() {
        assertEquals("          ", StringUtil.padding(10, -1));
    }

    @Test
    public void testPaddingWithZeroWidth() {
        assertEquals("", StringUtil.padding(0, 20));
    }

    @Test
    public void testPaddingWithNegativeWidth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.padding(-1, 20);
        });
        assertEquals("width must be >= 0", exception.getMessage());
    }

    @Test
    public void testPaddingWithNegativeMaxPaddingWidth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.padding(10, -2);
        });
        assertEquals("maxPaddingWidth must be >= -1", exception.getMessage());
    }
}
