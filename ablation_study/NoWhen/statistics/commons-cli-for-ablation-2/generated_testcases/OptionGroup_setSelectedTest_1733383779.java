
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_setSelectedTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testSetSelectedWithNullOption() {
        optionGroup.setSelected(null);
        assertNull(optionGroup.getSelected());
        assertFalse(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedWithNewOption() {
        Option optionA = new Option("a", "Option A");
        optionGroup.addOption(optionA);

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedWithSameOption() {
        Option optionA = new Option("a", "Option A");
        optionGroup.addOption(optionA);

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());
    }

    @Test
    public void testSetSelectedWithDifferentOption() {
        Option optionA = new Option("a", "Option A");
        Option optionB = new Option("b", "Option B");
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
        assertTrue(optionGroup.isSelected());

        assertThrows(AlreadySelectedException.class, () -> {
            optionGroup.setSelected(optionB);
        });
    }
}
