
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_isFalseTest {

    @Test
    public void testIsFalse_withFalseValue() {
        Validate.isFalse(false); // No exception should be thrown
    }

    @Test
    public void testIsFalse_withTrueValue() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.isFalse(true);
        });
        assertEquals("Must be false", exception.getMessage());
    }
}
