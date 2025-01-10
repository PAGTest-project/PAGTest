
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
        // Given
        field.setFormName("testForm");
        field.setFieldName("testField");
        field.setPage(1);
        field.getParameters().put("testParam", new Object());

        // When
        field.clear();

        // Then
        assertNull(field.getFormName());
        assertNull(field.getFieldName());
        assertEquals(0, field.getPage());
        assertTrue(field.getParameters().get("testParam") == null);
    }
}
