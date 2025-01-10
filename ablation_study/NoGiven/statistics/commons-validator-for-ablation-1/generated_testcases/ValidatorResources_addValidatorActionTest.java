
package org.apache.commons.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        };
        validatorAction = Mockito.mock(ValidatorAction.class);
        log = Mockito.mock(Log.class);
        when(validatorAction.getName()).thenReturn("testAction");
        when(validatorAction.getClassname()).thenReturn("TestClass");
        when(validatorResources.getLog()).thenReturn(log);
    }

    @Test
    void testAddValidatorAction() {
        // Given
        when(log.isDebugEnabled()).thenReturn(true);

        // When
        validatorResources.addValidatorAction(validatorAction);

        // Then
        verify(validatorAction).init();
        verify(log).debug("Add ValidatorAction: testAction,TestClass");
        assertEquals(validatorAction, validatorResources.getValidatorAction("testAction"));
    }
}
