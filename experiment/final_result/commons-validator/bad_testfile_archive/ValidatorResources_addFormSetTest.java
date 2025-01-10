
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
    private Log log;

    @BeforeEach
    public void setUp() {
        validatorResources = Mockito.spy(new ValidatorResources());
        formSet = new FormSet();
        log = Mockito.mock(Log.class);
        doReturn(log).when(validatorResources).getLog();
    }

    @Test
    public void testAddFormSetDefault() {
        // Given
        doReturn("").when(validatorResources).buildKey(formSet);
        doReturn(true).when(log).isWarnEnabled();

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.defaultFormSet);
        verify(log).warn("Overriding default FormSet definition.");
    }

    @Test
    public void testAddFormSetNonDefaultNew() {
        // Given
        String key = "en_US";
        doReturn(key).when(validatorResources).buildKey(formSet);
        doReturn(true).when(log).isDebugEnabled();
        doReturn(new HashMap<>()).when(validatorResources).getFormSets();

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.getFormSets().get(key));
        verify(log).debug("Adding FormSet '" + formSet + "'.");
    }

    @Test
    public void testAddFormSetNonDefaultOverride() {
        // Given
        String key = "en_US";
        doReturn(key).when(validatorResources).buildKey(formSet);
        doReturn(true).when(log).isWarnEnabled();
        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, new FormSet());
        doReturn(formSets).when(validatorResources).getFormSets();

        // When
        validatorResources.addFormSet(formSet);

        // Then
        assertEquals(formSet, validatorResources.getFormSets().get(key));
        verify(log).warn("Overriding FormSet definition. Duplicate for locale: " + key);
    }
}
