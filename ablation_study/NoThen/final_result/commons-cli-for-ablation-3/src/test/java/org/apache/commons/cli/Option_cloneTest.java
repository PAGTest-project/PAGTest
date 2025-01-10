
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_cloneTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("f").hasArg().numberOfArgs(2).build();
    }

    @Test
    public void testCloneWithValues() {
        option.processValue("value1");
        option.processValue("value2");

        Option clonedOption = (Option) option.clone();

        assertNotSame(option, clonedOption);
        assertEquals(option.getValues().length, clonedOption.getValues().length);
        assertEquals(option.getValues()[0], clonedOption.getValues()[0]);
        assertEquals(option.getValues()[1], clonedOption.getValues()[1]);
    }

    @Test
    public void testCloneWithoutValues() {
        Option clonedOption = (Option) option.clone();

        assertNotSame(option, clonedOption);
        assertNull(clonedOption.getValues());
    }

    @Test
    public void testCloneAndClearValues() {
        option.processValue("value1");
        option.processValue("value2");

        Option clonedOption = (Option) option.clone();
        clonedOption.clearValues();

        assertNotSame(option, clonedOption);
        assertNull(clonedOption.getValues());
    }

    @Test
    public void testCloneAndProcessValue() {
        option.processValue("value1");

        Option clonedOption = (Option) option.clone();
        clonedOption.processValue("value2");

        assertNotSame(option, clonedOption);
        assertEquals(1, option.getValues().length);
        assertEquals(2, clonedOption.getValues().length);
    }
}
