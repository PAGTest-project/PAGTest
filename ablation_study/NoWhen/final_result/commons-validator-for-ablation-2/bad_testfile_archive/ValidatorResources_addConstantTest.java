
package org.apache.commons.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ValidatorResources_addConstantTest {

    private ValidatorResources validatorResources;
    private Log mockLog;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources() {
            @Override
            public Log getLog() {
                return mockLog;
            }
        };
        mockLog = Mockito.mock(Log.class);
    }

    @Test
    public void testAddConstantWithDebugEnabled() {
        // Given
        when(mockLog.isDebugEnabled()).thenReturn(true);
        String name = "testName";
        String value = "testValue";

        // When
        validatorResources.addConstant(name, value);

        // Then
        assertEquals(value, validatorResources.getConstants().get(name));
    }

    @Test
    public void testAddConstantWithDebugDisabled() {
        // Given
        when(mockLog.isDebugEnabled()).thenReturn(false);
        String name = "testName";
        String value = "testValue";

        // When
        validatorResources.addConstant(name, value);

        // Then
        assertEquals(value, validatorResources.getConstants().get(name));
    }
}
