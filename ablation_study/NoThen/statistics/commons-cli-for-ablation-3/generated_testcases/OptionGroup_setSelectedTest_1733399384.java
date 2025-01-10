
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
    public void testSetSelectedSuccess() {
        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedAlreadySelected() {
        optionGroup.setSelected(optionA);
        try {
            optionGroup.setSelected(optionB);
            fail("Expected AlreadySelectedException was not thrown");
        } catch (AlreadySelectedException e) {
            assertEquals(optionGroup, e.getOptionGroup());
            assertEquals(optionB, e.getOption());
        }
    }

    @Test
    public void testSetSelectedNull() {
        optionGroup.setSelected(optionA);
        optionGroup.setSelected(null);
        assertNull(optionGroup.getSelected());
    }

    @Test
    public void testSetSelectedSameOption() {
        optionGroup.setSelected(optionA);
        optionGroup.setSelected(optionA);
        assertEquals("a", optionGroup.getSelected());
    }
}
