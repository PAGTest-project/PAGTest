
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelpFormatter_getDescriptionTest {

    @Test
    public void testGetDescriptionWithNonNullDescription() {
        Option option = new Option("o", "option", false, "This is a description");
        assertEquals("This is a description", HelpFormatter.getDescription(option));
    }

    @Test
    public void testGetDescriptionWithNullDescription() {
        Option option = new Option("o", "option", false, null);
        assertEquals("", HelpFormatter.getDescription(option));
    }
}
