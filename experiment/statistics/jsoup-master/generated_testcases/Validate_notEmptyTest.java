
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_notEmptyTest {

    @Test
    public void testNotEmpty_withNonEmptyString() {
        Validate.notEmpty("non-empty string");
    }

    @Test
    public void testNotEmpty_withNullString() {
        assertThrows(ValidationException.class, () -> Validate.notEmpty(null));
    }

    @Test
    public void testNotEmpty_withEmptyString() {
        assertThrows(ValidationException.class, () -> Validate.notEmpty(""));
    }
}
