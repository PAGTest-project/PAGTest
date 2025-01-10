
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_paddingTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testPaddingWithValidWidth() {
        assertEquals("   ", StringUtil.padding(3, 10));
        assertEquals("                    ", StringUtil.padding(20, 30));
    }

    @Test
    public void testPaddingWithMaxPaddingWidth() {
        assertEquals("          ", StringUtil.padding(20, 10));
        assertEquals("                    ", StringUtil.padding(30, 20));
    }

    @Test
    public void testPaddingWithUnlimitedWidth() {
        assertEquals("                              ", StringUtil.padding(30, -1));
        assertEquals("                                   ", StringUtil.padding(31, -1));
    }

    @Test
    public void testPaddingWithZeroWidth() {
        assertEquals("", StringUtil.padding(0, 10));
        assertEquals("", StringUtil.padding(0, -1));
    }

    @Test
    public void testPaddingWithNegativeWidth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.padding(-1, 10);
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
