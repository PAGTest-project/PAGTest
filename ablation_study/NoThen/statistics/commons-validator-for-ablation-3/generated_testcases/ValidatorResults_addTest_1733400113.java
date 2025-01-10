
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorResults_addTest {

    private ValidatorResults validatorResults;
    private Field field;
    private static final String VALIDATOR_NAME = "required";

    @BeforeEach
    public void setUp() {
        validatorResults = new ValidatorResults();
        field = new Field();
        field.setKey("testField");
    }

    @Test
    public void testAddNewValidatorResult() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());

        assertNotNull(result, "ValidatorResult should not be null.");
        assertTrue(result.containsAction(VALIDATOR_NAME), "ValidatorResult should contain the 'required' action.");
        assertTrue(result.isValid(VALIDATOR_NAME), "ValidatorResult for the 'required' action should be valid.");
        assertEquals("validValue", result.getResult(VALIDATOR_NAME), "Result value should match.");
    }

    @Test
    public void testAddExistingValidatorResult() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.add(field, "anotherValidator", false, "invalidValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());

        assertNotNull(result, "ValidatorResult should not be null.");
        assertTrue(result.containsAction(VALIDATOR_NAME), "ValidatorResult should contain the 'required' action.");
        assertTrue(result.isValid(VALIDATOR_NAME), "ValidatorResult for the 'required' action should be valid.");
        assertEquals("validValue", result.getResult(VALIDATOR_NAME), "Result value should match.");

        assertTrue(result.containsAction("anotherValidator"), "ValidatorResult should contain the 'anotherValidator' action.");
        assertFalse(result.isValid("anotherValidator"), "ValidatorResult for the 'anotherValidator' action should be invalid.");
        assertEquals("invalidValue", result.getResult("anotherValidator"), "Result value should match.");
    }

    @Test
    public void testAddWithClear() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.clear();
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());

        assertNull(result, "ValidatorResult should be null after clear.");
    }

    @Test
    public void testAddWithMerge() {
        ValidatorResults otherResults = new ValidatorResults();
        Field otherField = new Field();
        otherField.setKey("otherField");
        otherResults.add(otherField, VALIDATOR_NAME, false, "invalidValue");

        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.merge(otherResults);

        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        ValidatorResult otherResult = validatorResults.getValidatorResult(otherField.getKey());

        assertNotNull(result, "ValidatorResult should not be null.");
        assertTrue(result.containsAction(VALIDATOR_NAME), "ValidatorResult should contain the 'required' action.");
        assertTrue(result.isValid(VALIDATOR_NAME), "ValidatorResult for the 'required' action should be valid.");
        assertEquals("validValue", result.getResult(VALIDATOR_NAME), "Result value should match.");

        assertNotNull(otherResult, "Other ValidatorResult should not be null.");
        assertTrue(otherResult.containsAction(VALIDATOR_NAME), "Other ValidatorResult should contain the 'required' action.");
        assertFalse(otherResult.isValid(VALIDATOR_NAME), "Other ValidatorResult for the 'required' action should be invalid.");
        assertEquals("invalidValue", otherResult.getResult(VALIDATOR_NAME), "Result value should match.");
    }
}
