
package org.apache.commons.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ValidatorResources_addFormSetTest {

    private ValidatorResources validatorResources;
    private FormSet formSet;
    private Log log;

    @BeforeEach
    void setUp() {
        validatorResources = new ValidatorResources();
        formSet = mock(FormSet.class);
        log = mock(Log.class);
        when(validatorResources.getLog()).thenReturn(log);
    }

    @Test
    void testAddFormSet_DefaultFormSet() {
        when(validatorResources.buildKey(formSet)).thenReturn("");
        validatorResources.addFormSet(formSet);
        verify(log, never()).warn(anyString());
        verify(log, never()).debug(anyString());
    }

    @Test
    void testAddFormSet_NewFormSet() {
        when(validatorResources.buildKey(formSet)).thenReturn("en");
        when(validatorResources.getFormSets()).thenReturn(new FastHashMap());
        validatorResources.addFormSet(formSet);
        verify(log, never()).warn(anyString());
        verify(log, times(1)).debug("Adding FormSet '" + formSet + "'.");
    }

    @Test
    void testAddFormSet_OverridingFormSet() {
        when(validatorResources.buildKey(formSet)).thenReturn("en");
        FastHashMap formSets = new FastHashMap();
        formSets.put("en", formSet);
        when(validatorResources.getFormSets()).thenReturn(formSets);
        validatorResources.addFormSet(formSet);
        verify(log, times(1)).warn("Overriding FormSet definition. Duplicate for locale: en");
        verify(log, never()).debug(anyString());
    }
}
