
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_addOptionTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testAddOption() {
        Option option = new Option("o", "option", false, "Option description");
        optionGroup.addOption(option);

        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options);
        assertEquals(1, options.size());
        assertTrue(options.contains(option));
    }

    @Test
    public void testAddMultipleOptions() {
        Option option1 = new Option("o1", "option1", false, "Option 1 description");
        Option option2 = new Option("o2", "option2", false, "Option 2 description");
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);

        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options);
        assertEquals(2, options.size());
        assertTrue(options.contains(option1));
        assertTrue(options.contains(option2));
    }

    @Test
    public void testAddOptionWithNullKey() {
        Option option = new Option(null, "option", false, "Option description");
        optionGroup.addOption(option);

        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options);
        assertEquals(1, options.size());
        assertTrue(options.contains(option));
    }

    @Test
    public void testAddOptionWithEmptyKey() {
        Option option = new Option("o", "option", false, "Option description");
        optionGroup.addOption(option);

        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options);
        assertEquals(1, options.size());
        assertTrue(options.contains(option));
    }
}
