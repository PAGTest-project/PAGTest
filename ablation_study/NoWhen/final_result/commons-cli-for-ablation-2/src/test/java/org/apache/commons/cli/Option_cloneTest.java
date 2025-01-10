
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_cloneTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = Option.builder("o").longOpt("option").hasArg().build();
        option.getValuesList().add("value1");
        option.getValuesList().add("value2");
    }

    @Test
    public void testClone() {
        final Option clonedOption = (Option) option.clone();

        // Verify that the clone is not the same instance
        assertNotSame(option, clonedOption);

        // Verify that the clone has the same state
        assertEquals(option.getOpt(), clonedOption.getOpt());
        assertEquals(option.getLongOpt(), clonedOption.getLongOpt());
        assertEquals(option.getDescription(), clonedOption.getDescription());
        assertEquals(option.getValuesList(), clonedOption.getValuesList());

        // Verify that the clone is equal to the original
        assertTrue(option.equals(clonedOption));
        assertEquals(option.hashCode(), clonedOption.hashCode());
    }
}
