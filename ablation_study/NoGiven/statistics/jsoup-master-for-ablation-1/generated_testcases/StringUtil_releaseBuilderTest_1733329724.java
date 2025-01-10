
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_releaseBuilderTest {

    @Test
    public void testReleaseBuilder_SmallBuilder() {
        StringBuilder sb = new StringBuilder("test");
        String result = StringUtil.releaseBuilder(sb);
        assertEquals("test", result);
        assertEquals(0, sb.length());
    }

    @Test
    public void testReleaseBuilder_LargeBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < StringUtil.MaxBuilderSize + 1; i++) {
            sb.append("a");
        }
        String result = StringUtil.releaseBuilder(sb);
        assertEquals(StringUtil.MaxBuilderSize + 1, result.length());
        assertEquals(StringUtil.MaxBuilderSize + 1, sb.length());
    }
}
