
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
        int maxBuilderSize = 100; // Assuming MaxBuilderSize is 100
        for (int i = 0; i < maxBuilderSize + 1; i++) {
            sb.append("a");
        }
        String result = StringUtil.releaseBuilder(sb);
        assertEquals(String.valueOf(new char[maxBuilderSize + 1]).replace('\0', 'a'), result);
        assertEquals(maxBuilderSize + 1, sb.length()); // Ensure the StringBuilder is not emptied
    }

    @Test
    public void testReleaseBuilderWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.releaseBuilder(null);
        });
    }
}
