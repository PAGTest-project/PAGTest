
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_getValueTypeTest {

    @Test
    public void testGetValueType_Object() {
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueType('@'));
    }

    @Test
    public void testGetValueType_String() {
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueType(':'));
    }

    @Test
    public void testGetValueType_Number() {
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueType('%'));
    }

    @Test
    public void testGetValueType_Class() {
        assertEquals(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueType('+'));
    }

    @Test
    public void testGetValueType_Date() {
        assertEquals(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueType('#'));
    }

    @Test
    public void testGetValueType_ExistingFile() {
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueType('<'));
    }

    @Test
    public void testGetValueType_File() {
        assertEquals(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueType('>'));
    }

    @Test
    public void testGetValueType_Files() {
        assertEquals(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueType('*'));
    }

    @Test
    public void testGetValueType_URL() {
        assertEquals(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueType('/'));
    }

    @Test
    public void testGetValueType_Null() {
        assertNull(PatternOptionBuilder.getValueType('X'));
    }
}
