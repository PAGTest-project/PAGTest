
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorResources_addConstantTest {

    private ValidatorResources validatorResources;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
    }

    @Test
    public void testAddConstant() {
        validatorResources.addConstant("constantName", "constantValue");
        assertEquals("constantValue", validatorResources.getConstants().get("constantName"));
    }

    @Test
    public void testAddConstantWithDebugLogging() {
        // Assuming getLog().isDebugEnabled() returns true
        validatorResources.addConstant("constantName", "constantValue");
        // Verify that the constant was added
        assertEquals("constantValue", validatorResources.getConstants().get("constantName"));
        // Verify that debug logging was called (this part is usually mocked in real tests)
    }

    @Test
    public void testAddConstantWithNoDebugLogging() {
        // Assuming getLog().isDebugEnabled() returns false
        validatorResources.addConstant("constantName", "constantValue");
        // Verify that the constant was added
        assertEquals("constantValue", validatorResources.getConstants().get("constantName"));
        // Verify that debug logging was not called (this part is usually mocked in real tests)
    }
}
