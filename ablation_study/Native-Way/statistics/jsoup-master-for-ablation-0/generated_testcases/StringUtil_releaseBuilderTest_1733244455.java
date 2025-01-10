
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_releaseBuilderTest {

    private StringBuilder sb;

    @BeforeEach
    public void setUp() {
        sb = new StringBuilder("test string");
    }

    @Test
    public void testReleaseBuilderWithValidLength() {
        sb.setLength(StringUtil.MaxBuilderSize - 1);
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test string", result);
    }

    @Test
    public void testReleaseBuilderWithMaxLength() {
        sb.setLength(StringUtil.MaxBuilderSize);
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test string", result);
    }

    @Test
    public void testReleaseBuilderWithExceedingLength() {
        sb.setLength(StringUtil.MaxBuilderSize + 1);
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test string", result);
    }

    @Test
    public void testReleaseBuilderWithNull() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.releaseBuilder(null));
    }

    @Test
    public void testReleaseBuilderWithEmptyString() {
        sb.setLength(0);
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("", result);
    }
}
