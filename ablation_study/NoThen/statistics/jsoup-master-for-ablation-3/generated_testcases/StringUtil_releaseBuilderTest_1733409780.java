
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_releaseBuilderTest {

    private StringBuilder sb;

    @BeforeEach
    public void setUp() {
        sb = StringUtil.borrowBuilder();
    }

    @Test
    public void testReleaseBuilderWithNormalSize() {
        sb.append("test string");
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test string", result);
    }

    @Test
    public void testReleaseBuilderWithMaxSize() {
        for (int i = 0; i < StringUtil.MaxBuilderSize; i++) {
            sb.append("a");
        }
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("a".repeat(StringUtil.MaxBuilderSize), result);
    }

    @Test
    public void testReleaseBuilderWithExceedingMaxSize() {
        for (int i = 0; i < StringUtil.MaxBuilderSize + 1; i++) {
            sb.append("a");
        }
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("a".repeat(StringUtil.MaxBuilderSize + 1), result);
    }

    @Test
    public void testReleaseBuilderWithNull() {
        assertThrows(IllegalArgumentException.class, () -> StringUtil.releaseBuilder(null));
    }

    @Test
    public void testReleaseBuilderWithEmptyBuilder() {
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("", result);
    }
}
