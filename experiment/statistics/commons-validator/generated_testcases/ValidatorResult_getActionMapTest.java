
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ValidatorResult_getActionMapTest {

    private ValidatorResult validatorResult;

    @BeforeEach
    public void setUp() {
        validatorResult = new ValidatorResult(new Field());
    }

    @Test
    public void testGetActionMap() {
        // Given
        validatorResult.add("validator1", true, "result1");
        validatorResult.add("validator2", false, "result2");

        // When
        Map<String, ValidatorResult.ResultStatus> actionMap = validatorResult.getActionMap();

        // Then
        assertNotNull(actionMap);
        assertEquals(2, actionMap.size());
        assertTrue(actionMap.containsKey("validator1"));
        assertTrue(actionMap.containsKey("validator2"));
        assertTrue(actionMap.get("validator1").isValid());
        assertFalse(actionMap.get("validator2").isValid());
    }
}
