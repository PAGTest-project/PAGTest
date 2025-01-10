
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorResources_addValidatorActionTest {

    private ValidatorResources validatorResources;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
    }

    @Test
    public void testAddValidatorAction() {
        ValidatorAction va = new ValidatorAction();
        va.setName("testAction");
        va.setClassname("TestClass");

        validatorResources.addValidatorAction(va);

        ValidatorAction retrievedAction = validatorResources.getValidatorAction("testAction");
        assertNotNull(retrievedAction);
        assertEquals("testAction", retrievedAction.getName());
        assertEquals("TestClass", retrievedAction.getClassname());
    }

    @Test
    public void testAddValidatorActionWithDebugLogging() {
        ValidatorAction va = new ValidatorAction();
        va.setName("debugAction");
        va.setClassname("DebugClass");

        // Mocking the log to enable debug mode
        validatorResources.getLog().setLevel(org.apache.commons.logging.Log.DEBUG);

        validatorResources.addValidatorAction(va);

        ValidatorAction retrievedAction = validatorResources.getValidatorAction("debugAction");
        assertNotNull(retrievedAction);
        assertEquals("debugAction", retrievedAction.getName());
        assertEquals("DebugClass", retrievedAction.getClassname());
    }
}
