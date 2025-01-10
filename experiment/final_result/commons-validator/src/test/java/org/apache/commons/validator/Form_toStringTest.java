
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Form_toStringTest {

    @Test
    public void testToString() {
        // Given
        Form form = new Form();
        form.setName("TestForm");
        Field field1 = new Field();
        field1.setKey("field1");
        Field field2 = new Field();
        field2.setKey("field2");
        form.addField(field1);
        form.addField(field2);

        // When
        String result = form.toString();

        // Then
        String expected = "Form: TestForm\n" +
                          "\tField: \n" +
                          field1.toString() + "\n" +
                          "\tField: \n" +
                          field2.toString() + "\n";
        assertEquals(expected, result);
    }
}
