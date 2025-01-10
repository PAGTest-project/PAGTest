
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ValidatorResources_getFormTest {

    @Test
    public void testGetForm_ExistingLocale() {
        // Given
        ValidatorResources resources = new ValidatorResources();
        FormSet formSet = new FormSet();
        Form form = new Form();
        form.setName("testForm");
        formSet.addForm(form);
        resources.addFormSet(formSet);

        // When
        Form retrievedForm = resources.getForm(Locale.getDefault(), "testForm");

        // Then
        assertNotNull(retrievedForm);
    }

    @Test
    public void testGetForm_NonExistingLocale() {
        // Given
        ValidatorResources resources = new ValidatorResources();

        // When
        Form form = resources.getForm(Locale.getDefault(), "nonExistingForm");

        // Then
        assertNull(form);
    }
}
