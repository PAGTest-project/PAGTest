
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
        // Given
        validator.setFormName("testForm");
        validator.setFieldName("testField");
        validator.setPage(1);
        validator.setParameter("testParam", new Object());

        // When
        validator.clear();

        // Then
        assertNull(validator.getFormName());
        assertNull(validator.getFieldName());
        assertEquals(0, validator.getPage());
        assertTrue(validator.getParameterValue("testParam") == null);
    }
}
