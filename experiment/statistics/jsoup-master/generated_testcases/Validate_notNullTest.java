
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_notNullTest {

    @Test
    public void testNotNullWithNonNullObject() {
        // Given
        Object obj = new Object();

        // When and Then
        assertDoesNotThrow(() -> Validate.notNull(obj));
    }

    @Test
    public void testNotNullWithNullObject() {
        // Given
        Object obj = null;

        // When and Then
        ValidationException exception = assertThrows(ValidationException.class, () -> Validate.notNull(obj));
        assertEquals("Object must not be null", exception.getMessage());
    }
}
