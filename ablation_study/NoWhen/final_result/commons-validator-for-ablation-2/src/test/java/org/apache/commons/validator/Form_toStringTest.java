
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Form_toStringTest {

    private Form form;
    private Field field1;
    private Field field2;

    @BeforeEach
    public void setUp() {
        form = new Form();
        form.setName("TestForm");

        field1 = new Field();
        field1.setKey("field-1");
        field1.setProperty("property-1");

        field2 = new Field();
        field2.setKey("field-2");
        field2.setProperty("property-2");

        form.addField(field1);
        form.addField(field2);
    }

    @Test
    public void testToString() {
        String expected = "Form: TestForm\n" +
                          "\tField: \n" +
                          field1.toString() + "\n" +
                          "\tField: \n" +
                          field2.toString() + "\n";

        assertEquals(expected, form.toString());
    }

    @Test
    public void testToStringWithEmptyFields() {
        form.lFields = new ArrayList<>();
        String expected = "Form: TestForm\n";

        assertEquals(expected, form.toString());
    }

    @Test
    public void testToStringWithNullName() {
        form.setName(null);
        String expected = "Form: null\n" +
                          "\tField: \n" +
                          field1.toString() + "\n" +
                          "\tField: \n" +
                          field2.toString() + "\n";

        assertEquals(expected, form.toString());
    }

    @Test
    public void testToStringWithNullField() {
        form.lFields = new ArrayList<>();
        form.lFields.add(null);
        String expected = "Form: TestForm\n" +
                          "\tField: \n" +
                          "null\n";

        assertEquals(expected, form.toString());
    }
}
