
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_isTrueTest {

    @Test
    public void testIsTrue_withTrueValue() {
        Validate.isTrue(true);
    }

    @Test
    public void testIsTrue_withFalseValue() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.isTrue(false);
        });
        assertEquals("Must be true", exception.getMessage());
    }
}
