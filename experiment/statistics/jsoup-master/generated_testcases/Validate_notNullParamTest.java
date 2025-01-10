
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validate_notNullParamTest {

    @Test
    void testNotNullParamWithNonNullObject() {
        // Given
        Object obj = new Object();
        String param = "testParam";

        // When
        Validate.notNullParam(obj, param);

        // Then (no exception should be thrown)
    }

    @Test
    void testNotNullParamWithNullObject() {
        // Given
        Object obj = null;
        String param = "testParam";

        // When
        Exception exception = assertThrows(ValidationException.class, () -> {
            Validate.notNullParam(obj, param);
        });

        // Then
        assertEquals("The parameter 'testParam' must not be null.", exception.getMessage());
    }
}
