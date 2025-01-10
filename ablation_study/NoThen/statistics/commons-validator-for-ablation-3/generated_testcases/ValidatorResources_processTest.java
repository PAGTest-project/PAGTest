
package org.apache.commons.validator;

import org.apache.commons.collections.FastHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ValidatorResources_processTest {

    private ValidatorResources validatorResources;
    private FastHashMap hFormSets;
    private FastHashMap hConstants;
    private FastHashMap hActions;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
        hFormSets = mock(FastHashMap.class);
        hConstants = mock(FastHashMap.class);
        hActions = mock(FastHashMap.class);

        validatorResources.hFormSets = hFormSets;
        validatorResources.hConstants = hConstants;
        validatorResources.hActions = hActions;
    }

    @Test
    public void testProcess() {
        // Given
        // Mocks are set up in the setUp method

        // When
        validatorResources.process();

        // Then
        verify(hFormSets).setFast(true);
        verify(hConstants).setFast(true);
        verify(hActions).setFast(true);
        verify(validatorResources, times(1)).processForms();
    }
}
