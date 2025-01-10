
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_getValueTypeTest {

    @Test
    public void testGetValueTypeObject() {
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueType('@'));
    }

    @Test
    public void testGetValueTypeString() {
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueType(':'));
    }

    @Test
    public void testGetValueTypeNumber() {
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueType('%'));
    }

    @Test
    public void testGetValueTypeClass() {
        assertEquals(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueType('+'));
    }

    @Test
    public void testGetValueTypeDate() {
        assertEquals(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueType('#'));
    }

    @Test
    public void testGetValueTypeExistingFile() {
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueType('<'));
    }

    @Test
    public void testGetValueTypeFile() {
        assertEquals(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueType('>'));
    }

    @Test
    public void testGetValueTypeFiles() {
        assertEquals(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueType('*'));
    }

    @Test
    public void testGetValueTypeURL() {
        assertEquals(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueTypeNull() {
        assertNull(PatternOptionBuilder.getValueType('X'));
    }
}
