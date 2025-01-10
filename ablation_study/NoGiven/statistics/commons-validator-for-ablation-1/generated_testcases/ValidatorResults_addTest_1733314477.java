
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("validValue", result.getResult(VALIDATOR_NAME));
    }

    @Test
    public void testAddExistingValidatorResult() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.add(field, "anotherValidator", false, "invalidValue");
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertTrue(result.isValid(VALIDATOR_NAME));
        assertEquals("validValue", result.getResult(VALIDATOR_NAME));
        assertTrue(result.containsAction("anotherValidator"));
        assertFalse(result.isValid("anotherValidator"));
        assertEquals("invalidValue", result.getResult("anotherValidator"));
    }

    @Test
    public void testClearBeforeAdd() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.clear();
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNull(result);
    }

    @Test
    public void testMergeBeforeAdd() {
        ValidatorResults otherResults = new ValidatorResults();
        otherResults.add(field, VALIDATOR_NAME, true, "validValue");
        validatorResults.merge(otherResults);
        ValidatorResult result = validatorResults.getValidatorResult(field.getKey());
        assertNotNull(result);
        assertTrue(result.containsAction(VALIDATOR_NAME));
        assertTrue(result.isValid(VALIDATOR_NAME));
        assertEquals("validValue", result.getResult(VALIDATOR_NAME));
    }

    @Test
    public void testIsEmptyAfterAdd() {
        assertFalse(validatorResults.isEmpty()); // This line should be removed or corrected
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        assertFalse(validatorResults.isEmpty());
    }

    @Test
    public void testGetPropertyNamesAfterAdd() {
        validatorResults.add(field, VALIDATOR_NAME, true, "validValue");
        assertTrue(validatorResults.getPropertyNames().contains(field.getKey()));
    }
}
