
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
    public void testCreateValue_Long() throws ParseException {
        Long expected = 123L;
        Long actual = TypeHandler.createValue("123", Long.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_Integer() throws ParseException {
        Integer expected = 456;
        Integer actual = TypeHandler.createValue("456", Integer.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_File() throws ParseException {
        File expected = new File("/tmp/test.txt");
        File actual = TypeHandler.createValue("/tmp/test.txt", File.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_URL() throws ParseException {
        URL expected = new URL("http://example.com");
        URL actual = TypeHandler.createValue("http://example.com", URL.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_BigInteger() throws ParseException {
        BigInteger expected = new BigInteger("12345678901234567890");
        BigInteger actual = TypeHandler.createValue("12345678901234567890", BigInteger.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_BigDecimal() throws ParseException {
        BigDecimal expected = new BigDecimal("1234567890.1234567890");
        BigDecimal actual = TypeHandler.createValue("1234567890.1234567890", BigDecimal.class);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateValue_InvalidInput() {
        assertThrows(ParseException.class, () -> {
            TypeHandler.createValue("invalid", Long.class);
        });
    }
}
