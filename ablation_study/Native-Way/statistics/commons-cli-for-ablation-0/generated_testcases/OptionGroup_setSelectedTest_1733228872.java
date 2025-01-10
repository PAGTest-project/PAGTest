
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
        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());

        optionGroup.setSelected(null);
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithSameOption() throws AlreadySelectedException {
        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedWithDifferentOption() {
        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());

        assertThrows(AlreadySelectedException.class, () -> {
            optionGroup.setSelected(optionB);
        });
    }

    @Test
    public void testSetSelectedWithNoOptionSelected() throws AlreadySelectedException {
        assertNull(optionGroup.getSelected());

        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
    }
}
