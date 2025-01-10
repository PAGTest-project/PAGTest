
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternOptionBuilder_getValueClassTest {

    @Test
    public void testGetValueClass() {
        // Test for '@' character
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, PatternOptionBuilder.getValueClass('@'));

        // Test for ':' character
        assertEquals(PatternOptionBuilder.STRING_VALUE, PatternOptionBuilder.getValueClass(':'));

        // Test for '%' character
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, PatternOptionBuilder.getValueClass('%'));

        // Test for '+' character
        assertEquals(PatternOptionBuilder.CLASS_VALUE, PatternOptionBuilder.getValueClass('+'));

        // Test for '#' character
        assertEquals(PatternOptionBuilder.DATE_VALUE, PatternOptionBuilder.getValueClass('#'));

        // Test for '<' character
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, PatternOptionBuilder.getValueClass('<'));

        // Test for '>' character
        assertEquals(PatternOptionBuilder.FILE_VALUE, PatternOptionBuilder.getValueClass('>'));

        // Test for '*' character
        assertEquals(PatternOptionBuilder.FILES_VALUE, PatternOptionBuilder.getValueClass('*'));

        // Test for '/' character
        assertEquals(PatternOptionBuilder.URL_VALUE, PatternOptionBuilder.getValueClass('/'));

        // Test for an unsupported character
        assertNull(PatternOptionBuilder.getValueClass('X'));
    }
}
