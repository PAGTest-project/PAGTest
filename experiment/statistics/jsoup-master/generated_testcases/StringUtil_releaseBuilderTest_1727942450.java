
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_releaseBuilderTest {

    @Test
    public void testReleaseBuilderWithValidSize() {
        StringBuilder sb = new StringBuilder("test");
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test", result);
        assertEquals(0, sb.length()); // Ensure the StringBuilder is emptied
    }

    @Test
    public void testReleaseBuilderWithExceedingSize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < StringUtil.getMaxBuilderSize() + 1; i++) {
            sb.append("a");
        }
        String result = StringUtil.releaseBuilder(sb);
        assertEquals(String.valueOf(new char[StringUtil.getMaxBuilderSize() + 1]).replace('\0', 'a'), result);
        assertEquals(StringUtil.getMaxBuilderSize() + 1, sb.length()); // Ensure the StringBuilder is not emptied
    }

    @Test
    public void testReleaseBuilderWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.releaseBuilder(null);
        });
    }
}
