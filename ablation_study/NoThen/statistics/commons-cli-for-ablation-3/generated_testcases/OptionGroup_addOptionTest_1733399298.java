
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Option optionA = OptionBuilder.create('a');
        Option optionB = OptionBuilder.create('b');

        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        assertNotNull(optionGroup.getNames(), "null names");
        assertEquals(2, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("a"));
        assertTrue(optionGroup.getNames().contains("b"));
    }

    @Test
    public void testAddOptionAndSetSelected() {
        Option optionA = OptionBuilder.create('a');
        Option optionB = OptionBuilder.create('b');

        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        optionGroup.setSelected(optionA);

        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testAddOptionAndCheckSelected() {
        Option optionA = OptionBuilder.create('a');
        Option optionB = OptionBuilder.create('b');

        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        assertFalse(optionGroup.isSelected());

        optionGroup.setSelected(optionA);

        assertTrue(optionGroup.isSelected());
    }
}
