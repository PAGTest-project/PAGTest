
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_ensureNotNullTest {

    @Test
    void testEnsureNotNullWithNonNullObject() {
        Object obj = new Object();
        Object result = Validate.ensureNotNull(obj);
        assertEquals(obj, result);
    }

    @Test
    void testEnsureNotNullWithNullObject() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.ensureNotNull(null);
        });
        assertEquals("Object must not be null", exception.getMessage());
    }
}
