
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class TypeHandler_createValueTest {

    @Test
    public void testCreateValueSuccess() throws ParseException, MalformedURLException {
        String input = "http://example.com";
        URL expected = new URL(input);
        assertEquals(expected, TypeHandler.createValue(input, URL.class));
    }

    @Test
    public void testCreateValueThrowsParseException() {
        String invalidInput = "invalidUrl";
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue(invalidInput, URL.class);
        });
    }
}
