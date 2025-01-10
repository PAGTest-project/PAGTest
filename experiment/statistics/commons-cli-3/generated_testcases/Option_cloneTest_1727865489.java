
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_cloneTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("f", "foo", false, "description");
    }

    @Test
    public void testCloneSuccess() throws CloneNotSupportedException {
        option.addValueForProcessing("value1");
        option.addValueForProcessing("value2");

        Option clonedOption = (Option) option.clone();

        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getLongOpt(), clonedOption.getLongOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertEquals(option.getValuesList(), clonedOption.getValuesList());
        assertNotSame(option.getValuesList(), clonedOption.getValuesList());
    }

    @Test
    public void testCloneException() {
        option.clearValues(); // Simulate a state that would cause CloneNotSupportedException

        assertThrows(UnsupportedOperationException.class, () -> option.clone());
    }
}
