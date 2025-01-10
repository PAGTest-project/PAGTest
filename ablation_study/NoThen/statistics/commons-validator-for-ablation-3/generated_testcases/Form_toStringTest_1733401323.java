
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Form_toStringTest {

    private Form form;
    private Field field1;
    private Field field2;

    @BeforeEach
    public void setUp() {
        form = new Form();
        form.setName("TestForm");

        field1 = new Field();
        field1.setKey("field1");

        field2 = new Field();
        field2.setKey("field2");

        form.addField(field1);
        form.addField(field2);
    }

    @Test
    public void testToStringWithFields() {
        String expected = "Form: TestForm\n" +
                          "\tField: \n" +
                          field1.toString() + "\n" +
                          "\tField: \n" +
                          field2.toString() + "\n";

        assertEquals(expected, form.toString());
    }

    @Test
    public void testToStringWithoutFields() {
        form.lFields = new ArrayList<>();
        String expected = "Form: TestForm\n";

        assertEquals(expected, form.toString());
    }

    @Test
    public void testToStringWithEmptyName() {
        form.setName("");
        String expected = "Form: \n" +
                          "\tField: \n" +
                          field1.toString() + "\n" +
                          "\tField: \n" +
                          field2.toString() + "\n";

        assertEquals(expected, form.toString());
    }
}
