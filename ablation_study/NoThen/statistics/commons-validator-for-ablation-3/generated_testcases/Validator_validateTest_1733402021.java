
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Validator_validateTest {

    private Validator validator;
    private ValidatorResources resources;
    private Form form;

    @BeforeEach
    void setUp() {
        resources = mock(ValidatorResources.class);
        form = mock(Form.class);
        validator = new Validator(resources, "testForm");
    }

    @Test
    void testValidateWithLocale() throws ValidatorException {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Validator.LOCALE_PARAM, Locale.US);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.US);

        // When
        when(resources.getForm(Locale.US, "testForm")).thenReturn(form);
        when(form.validate(anyMap(), anyMap(), anyInt(), anyString())).thenReturn(new ValidatorResults());

        // Then
        ValidatorResults results = validator.validate();
        assertNotNull(results);
        verify(resources).getForm(Locale.US, "testForm");
        verify(form).validate(anyMap(), anyMap(), anyInt(), anyString());
    }

    @Test
    void testValidateWithoutLocale() throws ValidatorException {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Validator.LOCALE_PARAM, null);
        validator.setParameter(Validator.LOCALE_PARAM, null);

        // When
        when(resources.getForm(Locale.getDefault(), "testForm")).thenReturn(form);
        when(form.validate(anyMap(), anyMap(), anyInt(), anyString())).thenReturn(new ValidatorResults());

        // Then
        ValidatorResults results = validator.validate();
        assertNotNull(results);
        verify(resources).getForm(Locale.getDefault(), "testForm");
        verify(form).validate(anyMap(), anyMap(), anyInt(), anyString());
    }

    @Test
    void testValidateFormNotFound() throws ValidatorException {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Validator.LOCALE_PARAM, Locale.US);
        validator.setParameter(Validator.LOCALE_PARAM, Locale.US);

        // When
        when(resources.getForm(Locale.US, "testForm")).thenReturn(null);

        // Then
        ValidatorResults results = validator.validate();
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(resources).getForm(Locale.US, "testForm");
        verify(form, never()).validate(anyMap(), anyMap(), anyInt(), anyString());
    }
}
