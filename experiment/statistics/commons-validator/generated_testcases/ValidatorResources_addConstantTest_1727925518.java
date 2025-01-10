
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
    private Log log;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
        log = Mockito.mock(Log.class);
        when(log.isDebugEnabled()).thenReturn(true);
        validatorResources.log = log;
    }

    @Test
    public void testAddConstant() {
        // Given
        String name = "testConstant";
        String value = "testValue";

        // When
        validatorResources.addConstant(name, value);

        // Then
        assertEquals(value, validatorResources.getConstants().get(name));
    }

    @Test
    public void testAddConstantWithDebugLogging() {
        // Given
        String name = "testConstant";
        String value = "testValue";

        // When
        validatorResources.addConstant(name, value);

        // Then
        Mockito.verify(log).debug("Adding Global Constant: " + name + "," + value);
        assertEquals(value, validatorResources.getConstants().get(name));
    }
}
