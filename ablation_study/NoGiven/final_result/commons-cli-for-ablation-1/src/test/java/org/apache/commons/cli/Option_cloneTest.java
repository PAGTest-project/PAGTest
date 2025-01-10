
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_cloneTest {
    private Option originalOption;

    @BeforeEach
    public void setUp() {
        originalOption = Option.builder("test")
                .longOpt("longTest")
                .hasArg()
                .argName("argName")
                .desc("description")
                .build();
        originalOption.getValuesList().add("value1");
        originalOption.getValuesList().add("value2");
    }

    @Test
    public void testClone() {
        Option clonedOption = (Option) originalOption.clone();

        // Ensure the cloned object is not the same instance
        assertNotSame(originalOption, clonedOption);

        // Ensure the cloned object is equal to the original
        assertTrue(originalOption.equals(clonedOption));

        // Ensure the hash code of the cloned object matches the original
        assertEquals(originalOption.hashCode(), clonedOption.hashCode());

        // Ensure the values list is deep copied
        assertNotSame(originalOption.getValuesList(), clonedOption.getValuesList());
        assertEquals(originalOption.getValuesList(), clonedOption.getValuesList());
    }
}
