
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorResult_getResultTest {

    private ValidatorResult validatorResult;

    @BeforeEach
    public void setUp() {
        validatorResult = new ValidatorResult(new Field());
    }

    @Test
    public void testGetResultWithExistingValidatorName() {
        // Given
        validatorResult.add("validator1", true, "result1");

        // When
        Object result = validatorResult.getResult("validator1");

        // Then
        assertEquals("result1", result);
    }

    @Test
    public void testGetResultWithNonExistingValidatorName() {
        // Given
        validatorResult.add("validator1", true, "result1");

        // When
        Object result = validatorResult.getResult("validator2");

        // Then
        assertNull(result);
    }
}
