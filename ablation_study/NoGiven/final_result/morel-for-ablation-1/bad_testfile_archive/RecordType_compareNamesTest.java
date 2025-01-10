
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordType_compareNamesTest {

    @Test
    public void testCompareNames_DifferentIntegers() {
        // Given
        String o1 = "123";
        String o2 = "456";

        // When
        int result = RecordType.compareNames(o1, o2);

        // Then
        assertEquals(-1, result);
    }

    @Test
    public void testCompareNames_SameIntegersDifferentStrings() {
        // Given
        String o1 = "123";
        String o2 = "123a";

        // When
        int result = RecordType.compareNames(o1, o2);

        // Then
        assertEquals(-1, result);
    }

    @Test
    public void testCompareNames_NonIntegerStrings() {
        // Given
        String o1 = "abc";
        String o2 = "def";

        // When
        int result = RecordType.compareNames(o1, o2);

        // Then
        assertEquals(-1, result);
    }
}
