
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
        validatorResources = Mockito.spy(new ValidatorResources());
        formSet = new FormSet();
    }

    @Test
    public void testAddFormSetDefault() {
        // Given
        doReturn("").when(validatorResources).buildKey(formSet);
        doReturn(true).when(validatorResources).getLog().isWarnEnabled();

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
        doReturn(key).when(validatorResources).buildKey(formSet);
        doReturn(true).when(validatorResources).getLog().isDebugEnabled();
        doReturn(new HashMap<>()).when(validatorResources).getFormSets();

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
        doReturn(key).when(validatorResources).buildKey(formSet);
        doReturn(true).when(validatorResources).getLog().isWarnEnabled();
        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, new FormSet());
        doReturn(formSets).when(validatorResources).getFormSets();

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.getFormSets().get(key));
        verify(validatorResources.getLog()).warn("Overriding FormSet definition. Duplicate for locale: " + key);
    }
}
