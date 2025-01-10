
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
    public void testSetSelectedWithNullOption() throws AlreadySelectedException {
        optionGroup.setSelected(optionA); // Select an option first
        optionGroup.setSelected(null); // Then reset it
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithSameOption() throws AlreadySelectedException {
        optionGroup.setSelected(optionA); // Select optionA
        optionGroup.setSelected(optionA); // Re-select optionA
        assertEquals(optionA.getKey(), optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithDifferentOption() {
        try {
            optionGroup.setSelected(optionA); // Select optionA
            optionGroup.setSelected(optionB); // Try to select optionB
            fail("Expected AlreadySelectedException to be thrown");
        } catch (AlreadySelectedException e) {
            assertEquals(optionGroup, e.getOptionGroup());
            assertEquals(optionB, e.getOption());
        }
    }
}
