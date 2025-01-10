
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertTrue(result.isValid(VALIDATOR_NAME));
    }

    @Test
    public void testAddExistingValidatorResult() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.add(field, "anotherValidator", false, "invalidValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertTrue(result.isValid(VALIDATOR_NAME));
        assertTrue(result.containsAction("anotherValidator"));
        assertFalse(result.isValid("anotherValidator"));
    }

    @Test
    public void testAddWithNullValue() {
        validatorResults.add(field, VALIDATOR_NAME, true, null);
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertTrue(result.isValid(VALIDATOR_NAME));
    }

    @Test
    public void testAddWithFalseResult() {
        validatorResults.add(field, VALIDATOR_NAME, false, "invalidValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertFalse(result.isValid(VALIDATOR_NAME));
    }
}
