
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validator_clearTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testClear() {
        // Set initial values
        field.property = "testForm";
        field.key = "testField";
        field.page = 1;
        field.parameters.put("testParam", "testValue");

        // Call clear method
        field.clear();

        // Assertions to verify state reset
        assertNull(field.property, "Form name should be null after clear.");
        assertNull(field.key, "Field name should be null after clear.");
        assertEquals(0, field.page, "Page should be 0 after clear.");
        assertTrue(field.parameters.isEmpty(), "Parameters should be reset after clear.");
    }
}
