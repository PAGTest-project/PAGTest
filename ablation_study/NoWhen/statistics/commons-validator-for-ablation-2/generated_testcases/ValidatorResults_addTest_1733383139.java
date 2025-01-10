
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
    public void testAddWithEmptyValidatorName() {
        validatorResults.add(field, "", true, "validValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(""));
        assertTrue(result.isValid(""));
    }

    @Test
    public void testAddWithNullField() {
        validatorResults.add(null, VALIDATOR_NAME, true, "validValue");
        assertTrue(validatorResults.isEmpty());
    }

    @Test
    public void testAddWithNullValidatorName() {
        validatorResults.add(field, null, true, "validValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(null));
        assertTrue(result.isValid(null));
    }

    @Test
    public void testAddWithFalseResult() {
        validatorResults.add(field, VALIDATOR_NAME, false, "invalidValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertFalse(result.isValid(VALIDATOR_NAME));
    }

    @Test
    public void testAddMultipleFields() {
        Field anotherField = new Field();
        anotherField.setKey("anotherField");
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.add(anotherField, VALIDATOR_NAME, false, "invalidValue");
        ValidatorResult result1 = validatorResults.getValidatorResult(field.getKey());
        ValidatorResult result2 = validatorResults.getValidatorResult(anotherField.getKey());
        assertNotNull(result1);
        assertNotNull(result2);
        assertTrue(result1.containsAction(VALIDATOR_NAME));
        assertTrue(result1.isValid(VALIDATOR_NAME));
        assertTrue(result2.containsAction(VALIDATOR_NAME));
        assertFalse(result2.isValid(VALIDATOR_NAME));
    }
}
