
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        option.values.add("value1");
        option.values.add("value2");

        Option clonedOption = (Option) option.clone();

        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getLongOpt(), clonedOption.getLongOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertEquals(option.values, clonedOption.values);
        assertNotSame(option.values, clonedOption.values);
    }

    @Test
    public void testCloneException() {
        option.values = null; // Simulate a state that would cause CloneNotSupportedException

        assertThrows(UnsupportedOperationException.class, () -> option.clone());
    }
}
