
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_setSelectedTest {

    private OptionGroup optionGroup;
    private Option optionA;
    private Option optionB;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
        optionA = Option.builder("a").build();
        optionB = Option.builder("b").build();
        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);
    }

    @Test
    public void testSetSelectedWithNullOption() {
        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());

        optionGroup.setSelected(null);
        assertFalse(optionGroup.isSelected());
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithSameOption() {
        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());

        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithDifferentOption() {
        optionGroup.setSelected(optionA);
        assertTrue(optionGroup.isSelected());
        assertEquals("a", optionGroup.getSelected());

        assertThrows(AlreadySelectedException.class, () -> {
            optionGroup.setSelected(optionB);
        });
    }
}
