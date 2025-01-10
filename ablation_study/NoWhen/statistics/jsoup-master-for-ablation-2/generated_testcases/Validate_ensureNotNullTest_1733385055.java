
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_ensureNotNullTest {

    @Test
    public void testEnsureNotNullWithNonNullObject() {
        Object obj = new Object();
        assertEquals(obj, Validate.ensureNotNull(obj));
    }

    @Test
    public void testEnsureNotNullWithNullObject() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.ensureNotNull(null);
        });
        String expectedMessage = "Object must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
