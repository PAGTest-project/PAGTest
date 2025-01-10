
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Validator_validateTest {

    @Test
    public void testValidateWithLocale() throws ValidatorException {
        // Given
        ValidatorResources resources = mock(ValidatorResources.class);
        Form form = mock(Form.class);
        Validator validator = new Validator(resources, "testForm");
        validator.setParameter(Validator.LOCALE_PARAM, Locale.US);

        when(resources.getForm(Locale.US, "testForm")).thenReturn(form);
        when(form.validate(anyMap(), anyMap(), anyInt(), anyString())).thenReturn(new ValidatorResults());

        // When
        ValidatorResults results = validator.validate();

        // Then
        assertNotNull(results);
        verify(resources).getForm(Locale.US, "testForm");
        verify(form).validate(anyMap(), anyMap(), anyInt(), anyString());
    }

    @Test
    public void testValidateWithoutLocale() throws ValidatorException {
        // Given
        ValidatorResources resources = mock(ValidatorResources.class);
        Form form = mock(Form.class);
        Validator validator = new Validator(resources, "testForm");

        when(resources.getForm(Locale.getDefault(), "testForm")).thenReturn(form);
        when(form.validate(anyMap(), anyMap(), anyInt(), anyString())).thenReturn(new ValidatorResults());

        // When
        ValidatorResults results = validator.validate();

        // Then
        assertNotNull(results);
        verify(resources).getForm(Locale.getDefault(), "testForm");
        verify(form).validate(anyMap(), anyMap(), anyInt(), anyString());
    }

    @Test
    public void testValidateFormNotFound() throws ValidatorException {
        // Given
        ValidatorResources resources = mock(ValidatorResources.class);
        Validator validator = new Validator(resources, "testForm");

        when(resources.getForm(Locale.getDefault(), "testForm")).thenReturn(null);

        // When
        ValidatorResults results = validator.validate();

        // Then
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(resources).getForm(Locale.getDefault(), "testForm");
    }
}
