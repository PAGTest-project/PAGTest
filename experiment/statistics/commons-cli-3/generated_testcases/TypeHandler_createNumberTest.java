
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeHandler_createNumberTest {

    @Test
    public void testCreateNumberValid() throws ParseException {
        String validNumber = "12345";
        Number number = TypeHandler.createNumber(validNumber);
        assertEquals(12345L, number);
    }

    @Test
    public void testCreateNumberInvalid() {
        String invalidNumber = "notANumber";
        assertThrows(ParseException.class, () -> {
            TypeHandler.createNumber(invalidNumber);
        });
    }
}
