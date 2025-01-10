
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class TypeHandler_createValueTest {

    @Test
    public void testCreateValueWithValidClass() throws ParseException {
        String className = "java.lang.String";
        Class<?> clazz = TypeHandler.createValue(className, Class.class);
        assertEquals(String.class, clazz);
    }

    @Test
    public void testCreateValueWithInvalidClass() {
        String invalidClassName = "InvalidClassName";
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue(invalidClassName, Class.class);
        });
    }

    @Test
    public void testCreateValueWithValidFile() throws ParseException {
        String filePath = "src/test/resources/testfile.txt";
        File file = TypeHandler.createValue(filePath, File.class);
        assertEquals(new File(filePath), file);
    }

    @Test
    public void testCreateValueWithInvalidFile() {
        String invalidFilePath = "invalid/path/to/file.txt";
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue(invalidFilePath, File.class);
        });
    }

    @Test
    public void testCreateValueWithValidURL() throws ParseException {
        String urlString = "http://example.com";
        URL url = TypeHandler.createValue(urlString, URL.class);
        assertEquals(new URL(urlString), url);
    }

    @Test
    public void testCreateValueWithInvalidURL() {
        String invalidUrlString = "invalid://example.com";
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue(invalidUrlString, URL.class);
        });
    }
}
