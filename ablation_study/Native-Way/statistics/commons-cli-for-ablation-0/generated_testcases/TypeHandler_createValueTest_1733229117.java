
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class TypeHandler_createValueTest {

    @Test
    public void testCreateValue_Class() throws ParseException {
        final Class<?> cls = getClass();
        assertEquals(cls, TypeHandler.createValue(cls.getName(), Class.class));
    }

    @Test
    public void testCreateValue_File() throws ParseException {
        final File file = new File("test.txt");
        assertEquals(file, TypeHandler.createValue(file.getPath(), File.class));
    }

    @Test
    public void testCreateValue_URL() throws ParseException {
        final URL url = new URL("http://example.com");
        assertEquals(url, TypeHandler.createValue(url.toString(), URL.class));
    }

    @Test
    public void testCreateValue_BigInteger() throws ParseException {
        final BigInteger bigInteger = new BigInteger("12345678901234567890");
        assertEquals(bigInteger, TypeHandler.createValue(bigInteger.toString(), BigInteger.class));
    }

    @Test
    public void testCreateValue_BigDecimal() throws ParseException {
        final BigDecimal bigDecimal = new BigDecimal("1234567890.1234567890");
        assertEquals(bigDecimal, TypeHandler.createValue(bigDecimal.toString(), BigDecimal.class));
    }

    @Test
    public void testCreateValue_InvalidInput() {
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue("invalid", URL.class);
        });
    }
}
