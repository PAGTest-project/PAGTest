
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Form_toStringTest {

    private static final String FORM_KEY = "FORM_KEY";
    private static final String FORM_KEY2 = "FORM_KEY2";
    private ValidatorResources resources;

    @BeforeEach
    protected void setUp() throws Exception {
        // Load resources
        try (InputStream in = this.getClass().getResourceAsStream("/org/apache/commons/validator/ExtensionTest-config.xml")) {
            resources = new ValidatorResources(in);
        }
    }

    @Test
    public void testToStringWithFields() {
        final Form form = resources.getForm(ValidatorResources.defaultLocale, FORM_KEY);
        assertNotNull(form, FORM_KEY + " is null.");

        String formString = form.toString();
        assertNotNull(formString, "toString() should not return null.");
        assertTrue(formString.contains("Form: " + form.getName()), "toString() should contain form name.");

        List<Field> fields = form.getFields();
        for (Field field : fields) {
            assertTrue(formString.contains("\tField: \n" + field.toString()), "toString() should contain each field.");
        }
    }

    @Test
    public void testToStringWithoutFields() {
        final Form form = new Form();
        form.setName("TestForm");

        String formString = form.toString();
        assertNotNull(formString, "toString() should not return null.");
        assertEquals("Form: TestForm\n", formString, "toString() should match expected format without fields.");
    }
}
