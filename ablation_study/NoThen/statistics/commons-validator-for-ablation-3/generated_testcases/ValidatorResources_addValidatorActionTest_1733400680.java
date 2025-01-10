
package org.apache.commons.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorResources_addValidatorActionTest {

    private ValidatorResources validatorResources;
    private ValidatorAction validatorAction;
    private Log log;

    @BeforeEach
    void setUp() {
        validatorResources = new ValidatorResources() {
            @Override
            public Log getLog() {
                return log;
            }

            @Override
            public Map<String, ValidatorAction> getActions() {
                return new HashMap<>();
            }
        };
        validatorAction = mock(ValidatorAction.class);
        log = mock(Log.class);
        when(validatorAction.getName()).thenReturn("testAction");
        when(validatorAction.getClassname()).thenReturn("TestClass");
        when(log.isDebugEnabled()).thenReturn(true);
    }

    @Test
    void testAddValidatorAction() {
        // Given
        Map<String, ValidatorAction> actions = validatorResources.getActions();

        // When
        validatorResources.addValidatorAction(validatorAction);

        // Then
        verify(validatorAction).init();
        verify(log).debug("Add ValidatorAction: testAction,TestClass");
        assertTrue(actions.containsKey("testAction"));
        assertSame(actions.get("testAction"), validatorAction);
    }
}
