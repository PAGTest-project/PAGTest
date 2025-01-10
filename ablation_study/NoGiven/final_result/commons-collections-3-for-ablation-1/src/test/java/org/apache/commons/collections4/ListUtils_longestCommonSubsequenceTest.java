
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListUtils_longestCommonSubsequenceTest {

    @Test
    public void testLongestCommonSubsequence_NonNullInput() {
        // Given
        CharSequence charSequenceA = "ABC";
        CharSequence charSequenceB = "AC";

        // When
        String result = ListUtils.longestCommonSubsequence(charSequenceA, charSequenceB);

        // Then
        assertEquals("AC", result);
    }

    @Test
    public void testLongestCommonSubsequence_NullInput() {
        // Given
        CharSequence charSequenceA = null;
        CharSequence charSequenceB = "AC";

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ListUtils.longestCommonSubsequence(charSequenceA, charSequenceB);
        });
    }
}
