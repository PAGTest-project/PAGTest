
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_notEmptyParamTest {

    @Test
    void testNotEmptyParamValid() {
        assertDoesNotThrow(() -> Validate.notEmptyParam("validString", "paramName"));
    }

    @Test
    void testNotEmptyParamNull() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            Validate.notEmptyParam(null, "paramName");
        });
        assertEquals("The 'paramName' parameter must not be empty.", exception.getMessage());
    }

    @Test
    void testNotEmptyParamEmpty() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            Validate.notEmptyParam("", "paramName");
        });
        assertEquals("The 'paramName' parameter must not be empty.", exception.getMessage());
    }
}
