
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validator_clearTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator(new ValidatorResources());
    }

    @Test
    public void testClear() {
        // Set initial values
        validator.setFormName("testForm");
        validator.setFieldName("testField");
        validator.setPage(1);
        validator.setParameter("testParam", "testValue");

        // Call clear method
        validator.clear();

        // Assertions to verify state reset
        assertNull(validator.getFormName(), "Form name should be null after clear.");
        assertNull(validator.getFieldName(), "Field name should be null after clear.");
        assertEquals(0, validator.getPage(), "Page should be 0 after clear.");
        assertTrue(validator.getParameterValue("testParam") == null, "Parameters should be reset after clear.");
    }
}
