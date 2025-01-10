
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Validator_clearTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorResources resources = new ValidatorResources();
        validator = new Validator(resources);
    }

    @Test
    public void testClear() {
        // Given
        validator.setFormName("testForm");
        validator.setFieldName("testField");
        validator.setPage(1);
        validator.setParameter("testParam", new Object());

        // When
        validator.clear();

        // Then
        assertNull(validator.getFormName(), "Form name should be null after clear.");
        assertNull(validator.getFieldName(), "Field name should be null after clear.");
        assertEquals(0, validator.getPage(), "Page should be 0 after clear.");
        assertTrue(validator.getParameterValue("testParam") == null, "Parameters should be cleared after clear.");
    }
}
