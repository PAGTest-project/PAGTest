
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListUtils_longestCommonSubsequenceTest {

    @Test
    public void testLongestCommonSubsequence_NonNullInput() {
        String result = ListUtils.longestCommonSubsequence("ABCBDAB", "BDCAB");
        assertEquals("BCAB", result);
    }

    @Test
    public void testLongestCommonSubsequence_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            ListUtils.longestCommonSubsequence(null, "BDCAB");
        });

        assertThrows(NullPointerException.class, () -> {
            ListUtils.longestCommonSubsequence("ABCBDAB", null);
        });
    }
}
