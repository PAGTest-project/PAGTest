
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class TypeHandler_createValueTest {

    @Test
    public void testCreateValueWithClass() throws ParseException {
        Class<?> clazz = TypeHandler.createValue("java.lang.String", Class.class);
        assertNotNull(clazz);
        assertEquals(String.class, clazz);
    }

    @Test
    public void testCreateValueWithFile() throws ParseException {
        File file = TypeHandler.createValue("src/test/resources/testfile.txt", File.class);
        assertNotNull(file);
        assertEquals("testfile.txt", file.getName());
    }

    @Test
    public void testCreateValueWithURL() throws ParseException {
        URL url = TypeHandler.createValue("https://www.example.com", URL.class);
        assertNotNull(url);
        assertEquals("https://www.example.com", url.toString());
    }

    @Test
    public void testCreateValueWithInvalidInput() {
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue("invalid", URL.class);
        });
    }
}
