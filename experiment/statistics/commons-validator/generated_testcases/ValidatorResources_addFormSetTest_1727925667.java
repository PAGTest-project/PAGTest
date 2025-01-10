
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ValidatorResources_addFormSetTest {

    private ValidatorResources validatorResources;
    private FormSet formSet;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
        formSet = new FormSet();
    }

    @Test
    public void testAddFormSetDefault() {
        // Given
        when(validatorResources.buildKey(formSet)).thenReturn("");
        when(validatorResources.getLog()).thenReturn(Mockito.mock(Log.class));
        when(validatorResources.getLog().isWarnEnabled()).thenReturn(true);

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.defaultFormSet);
        verify(validatorResources.getLog()).warn("Overriding default FormSet definition.");
    }

    @Test
    public void testAddFormSetNonDefaultNew() {
        // Given
        String key = "en_US";
        when(validatorResources.buildKey(formSet)).thenReturn(key);
        when(validatorResources.getLog()).thenReturn(Mockito.mock(Log.class));
        when(validatorResources.getLog().isDebugEnabled()).thenReturn(true);
        when(validatorResources.getFormSets()).thenReturn(new HashMap<>());

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.getFormSets().get(key));
        verify(validatorResources.getLog()).debug("Adding FormSet '" + formSet + "'.");
    }

    @Test
    public void testAddFormSetNonDefaultOverride() {
        // Given
        String key = "en_US";
        when(validatorResources.buildKey(formSet)).thenReturn(key);
        when(validatorResources.getLog()).thenReturn(Mockito.mock(Log.class));
        when(validatorResources.getLog().isWarnEnabled()).thenReturn(true);
        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, new FormSet());
        when(validatorResources.getFormSets()).thenReturn(formSets);

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.getFormSets().get(key));
        verify(validatorResources.getLog()).warn("Overriding FormSet definition. Duplicate for locale: " + key);
    }
}
