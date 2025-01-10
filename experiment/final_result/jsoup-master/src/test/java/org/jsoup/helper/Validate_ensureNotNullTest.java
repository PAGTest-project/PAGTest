
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_ensureNotNullTest {

    @Test
    public void testEnsureNotNull_WithNonNullObject() {
        Object obj = new Object();
        Object result = Validate.ensureNotNull(obj);
        assertEquals(obj, result);
    }

    @Test
    public void testEnsureNotNull_WithNullObject() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.ensureNotNull(null);
        });
        assertEquals("Object must not be null", exception.getMessage());
    }
}
