
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorResults_addTest {

    private ValidatorResults validatorResults;
    private Field field;

    @BeforeEach
    public void setUp() {
        validatorResults = new ValidatorResults();
        field = new Field();
        field.setKey("testKey");
    }

    @Test
    public void testAddNewValidatorResult() {
        validatorResults.add(field, "testValidator", true, "testValue");

        ValidatorResult result = validatorResults.getValidatorResult("testKey");
        assertNotNull(result, "ValidatorResult should not be null.");
        assertTrue(result.containsAction("testValidator"), "ValidatorResult should contain the 'testValidator' action.");
        assertEquals("testValue", result.getResult("testValidator"), "Result value should match.");
    }

    @Test
    public void testAddExistingValidatorResult() {
        validatorResults.add(field, "testValidator", true, "testValue");
        validatorResults.add(field, "anotherValidator", false, "anotherValue");

        ValidatorResult result = validatorResults.getValidatorResult("testKey");
        assertNotNull(result, "ValidatorResult should not be null.");
        assertTrue(result.containsAction("testValidator"), "ValidatorResult should contain the 'testValidator' action.");
        assertTrue(result.containsAction("anotherValidator"), "ValidatorResult should contain the 'anotherValidator' action.");
        assertEquals("testValue", result.getResult("testValidator"), "Result value should match.");
        assertEquals("anotherValue", result.getResult("anotherValidator"), "Result value should match.");
    }
}
