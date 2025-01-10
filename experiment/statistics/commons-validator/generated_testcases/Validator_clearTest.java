
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
        field.setProperty("testForm");
        field.setIndexedListProperty("testField");
        field.setPage(1);
        field.hVars.put("testParam", new Object());

        // When
        field.clear();

        // Then
        assertNull(field.getProperty(), "Form name should be null after clear.");
        assertNull(field.getIndexedListProperty(), "Field name should be null after clear.");
        assertEquals(0, field.getPage(), "Page should be 0 after clear.");
        assertTrue(field.hVars.get("testParam") == null, "Parameters should be cleared after clear.");
    }
}
