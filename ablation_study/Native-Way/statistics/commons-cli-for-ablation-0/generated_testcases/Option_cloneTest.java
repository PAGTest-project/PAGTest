
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_cloneTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o").hasArg().numberOfArgs(2).build();
        option.processValue("value1");
        option.processValue("value2");
    }

    @Test
    public void testClone() {
        Option clonedOption = (Option) option.clone();

        assertNotSame(option, clonedOption);
        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertEquals(option.getValuesList(), clonedOption.getValuesList());
    }

    @Test
    public void testCloneWithEmptyValues() {
        option.clearValues();
        Option clonedOption = (Option) option.clone();

        assertNotSame(option, clonedOption);
        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertTrue(clonedOption.getValuesList().isEmpty());
    }

    @Test
    public void testCloneWithNullValues() {
        option.clearValues();
        option.processValue(null);
        Option clonedOption = (Option) option.clone();

        assertNotSame(option, clonedOption);
        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertEquals(1, clonedOption.getValuesList().size());
        assertNull(clonedOption.getValuesList().get(0));
    }
}
