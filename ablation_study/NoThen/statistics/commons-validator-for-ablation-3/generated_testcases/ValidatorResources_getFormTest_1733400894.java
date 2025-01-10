
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ValidatorResources_getFormTest {

    private ValidatorResources validatorResources;
    private FormSet formSet;
    private Form form;

    @BeforeEach
    public void setUp() {
        validatorResources = new ValidatorResources();
        formSet = mock(FormSet.class);
        form = mock(Form.class);
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
        when(formSet.getForm(formKey)).thenReturn(form);
        validatorResources.hFormSets = new HashMap<>(formSets);

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
        String variant = "NY";
        String formKey = "testForm";
        String key = "en_US";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        when(formSet.getForm(formKey)).thenReturn(form);
        validatorResources.hFormSets = new HashMap<>(formSets);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_Language() {
        // Given
        String language = "en";
        String country = "US";
        String variant = "NY";
        String formKey = "testForm";
        String key = "en";

        Map<String, FormSet> formSets = new HashMap<>();
        formSets.put(key, formSet);
        when(formSet.getForm(formKey)).thenReturn(form);
        validatorResources.hFormSets = new HashMap<>(formSets);

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

        validatorResources.defaultFormSet = formSet;
        when(formSet.getForm(formKey)).thenReturn(form);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertEquals(form, result);
    }

    @Test
    public void testGetForm_NotFound() {
        // Given
        String language = "en";
        String country = "US";
        String variant = "NY";
        String formKey = "testForm";

        validatorResources.defaultFormSet = formSet;
        when(formSet.getForm(formKey)).thenReturn(null);

        // When
        Form result = validatorResources.getForm(language, country, variant, formKey);

        // Then
        assertNull(result);
    }
}
