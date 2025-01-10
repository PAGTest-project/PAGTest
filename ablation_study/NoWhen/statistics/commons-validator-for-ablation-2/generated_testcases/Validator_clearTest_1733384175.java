
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class Validator_clearTest {

    @Test
    public void testClear() {
        // Given
        Validator validator = new Validator(new ValidatorResources());
        validator.setFormName("testForm");
        validator.setFieldName("testField");
        validator.setParameter("testParam", "testValue");
        validator.setPage(1);

        // When
        validator.clear();

        // Then
        assertNull(validator.getFormName());
        assertNull(validator.getFieldName());
        assertTrue(validator.getParameterValue("testParam") == null);
        assertEquals(0, validator.getPage());
    }
}
