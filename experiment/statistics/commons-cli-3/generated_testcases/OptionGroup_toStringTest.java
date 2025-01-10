
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_toStringTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testToStringWithSingleOption() {
        Option option = new Option("a", "Option A");
        optionGroup.addOption(option);

        assertEquals("[-a Option A]", optionGroup.toString());
    }

    @Test
    public void testToStringWithMultipleOptions() {
        Option optionA = new Option("a", "Option A");
        Option optionB = new Option("b", "Option B");
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        assertEquals("[-a Option A, -b Option B]", optionGroup.toString());
    }

    @Test
    public void testToStringWithLongOption() {
        Option option = new Option(null, "long-option", false, "Long Option");
        optionGroup.addOption(option);

        assertEquals("[--long-option Long Option]", optionGroup.toString());
    }

    @Test
    public void testToStringWithNoOptions() {
        assertEquals("[]", optionGroup.toString());
    }
}
