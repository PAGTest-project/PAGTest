
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ValidatorResources_getFormTest {

    private ValidatorResources validatorResources;
    private FormSet formSet;
    private Form form;
    private Log log;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources() {
            @Override
            public Log getLog() {
                return log;
            }
        };
        formSet = mock(FormSet.class);
        form = mock(Form.class);
        log = mock(Log.class);
        LogFactory.getLog(ValidatorResources.class); // Ensure LogFactory is initialized
    }

    @Test
    public void testGetForm_LanguageCountryVariant() {
        // Given
        String language = "en";
        String country = "US";
        String variant = "NY";
        String formKey = "testForm";
        String key = "en_US_NY";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        validatorResources.hFormSets = new FastHashMap(formSets);

        when(formSet.getForm(formKey)).thenReturn(form);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_LanguageCountry() {
        // Given
        String language = "en";
        String country = "US";
        String variant = null;
        String formKey = "testForm";
        String key = "en_US";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        validatorResources.hFormSets = new FastHashMap(formSets);

        when(formSet.getForm(formKey)).thenReturn(form);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_Language() {
        // Given
        String language = "en";
        String country = null;
        String variant = null;
        String formKey = "testForm";
        String key = "en";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        validatorResources.hFormSets = new FastHashMap(formSets);

        when(formSet.getForm(formKey)).thenReturn(form);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_DefaultFormSet() {
        // Given
        String language = "en";
        String country = "US";
        String variant = "NY";
        String formKey = "testForm";
        String key = "en_US_NY";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, null);
        validatorResources.hFormSets = new FastHashMap(formSets);
        validatorResources.defaultFormSet = formSet;

        when(formSet.getForm(formKey)).thenReturn(form);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_FormNotFound() {
        // Given
        String language = "en";
        String country = "US";
        String variant = "NY";
        String formKey = "testForm";
        String key = "en_US_NY";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        validatorResources.hFormSets = new FastHashMap(formSets);

        when(formSet.getForm(formKey)).thenReturn(null);
        when(validatorResources.getLog().isWarnEnabled()).thenReturn(true);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertNull(result);
        Mockito.verify(validatorResources.getLog()).warn("Form 'testForm' not found for locale 'en_US_NY'");
    }
}
