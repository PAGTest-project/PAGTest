
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Var_cloneTest {

    @Test
    void testClone() {
        // Given
        Var original = new Var("testName", "testValue", "string");

        // When
        Var cloned = (Var) original.clone();

        // Then
        assertNotSame(original, cloned);
        assertEquals(original.getName(), cloned.getName());
        assertEquals(original.getValue(), cloned.getValue());
        assertEquals(original.getJsType(), cloned.getJsType());
    }
}
