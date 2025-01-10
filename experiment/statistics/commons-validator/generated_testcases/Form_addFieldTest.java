
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Form_addFieldTest {

    private Form form;
    private Field field;

    @BeforeEach
    public void setUp() {
        form = new Form();
        field = new Field();
        field.setKey("testKey");
    }

    @Test
    public void testAddField() {
        form.addField(field);

        assertTrue(form.containsField("testKey"), "Field should be added to the form.");
        assertEquals(field, form.getField("testKey"), "Retrieved field should match the added field.");
    }
}
