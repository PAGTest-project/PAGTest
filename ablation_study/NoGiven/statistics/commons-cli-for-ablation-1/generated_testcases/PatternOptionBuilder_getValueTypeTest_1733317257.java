
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_getValueTypeTest {

    @Test
    public void testGetValueTypeObject() {
        assertEquals(Object.class, PatternOptionBuilder.getValueType('@'));
    }

    @Test
    public void testGetValueTypeString() {
        assertEquals(String.class, PatternOptionBuilder.getValueType(':'));
    }

    @Test
    public void testGetValueTypeNumber() {
        assertEquals(Number.class, PatternOptionBuilder.getValueType('%'));
    }

    @Test
    public void testGetValueTypeClass() {
        assertEquals(Class.class, PatternOptionBuilder.getValueType('+'));
    }

    @Test
    public void testGetValueTypeDate() {
        assertEquals(java.util.Date.class, PatternOptionBuilder.getValueType('#'));
    }

    @Test
    public void testGetValueTypeExistingFile() {
        assertEquals(java.io.File.class, PatternOptionBuilder.getValueType('<'));
    }

    @Test
    public void testGetValueTypeFile() {
        assertEquals(java.io.File.class, PatternOptionBuilder.getValueType('>'));
    }

    @Test
    public void testGetValueTypeFiles() {
        assertEquals(java.io.File[].class, PatternOptionBuilder.getValueType('*'));
    }

    @Test
    public void testGetValueTypeURL() {
        assertEquals(java.net.URL.class, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueTypeNull() {
        assertNull(PatternOptionBuilder.getValueType('X'));
    }
}
