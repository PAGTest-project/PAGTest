
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorResult_isValidTest {

    private ValidatorResult validatorResult;

    @BeforeEach
    public void setUp() {
        validatorResult = new ValidatorResult(new Field());
    }

    @Test
    public void testIsValid_ValidAction() {
        validatorResult.add("validatorName", true, null);
        assertTrue(validatorResult.isValid("validatorName"));
    }

    @Test
    public void testIsValid_InvalidAction() {
        validatorResult.add("validatorName", false, null);
        assertFalse(validatorResult.isValid("validatorName"));
    }

    @Test
    public void testIsValid_NullAction() {
        assertFalse(validatorResult.isValid("nonExistentValidatorName"));
    }
}
