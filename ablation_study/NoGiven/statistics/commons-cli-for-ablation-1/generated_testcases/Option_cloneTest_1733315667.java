
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Option_cloneTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("opt", "description");
        option.setArgs(2);
        option.setOptionalArg(true);
        option.setType(Integer.class);
        option.setValues(new ArrayList<>(List.of("1", "2")));
    }

    @Test
    public void testClone() {
        // When
        Option clonedOption = (Option) option.clone();

        // Then
        assertNotSame(option, clonedOption);
        assertEquals(option, clonedOption);
        assertEquals(option.getValues(), clonedOption.getValues());
    }

    @Test
    public void testCloneWithClearValues() {
        // When
        option.clearValues();
        Option clonedOption = (Option) option.clone();

        // Then
        assertNotSame(option, clonedOption);
        assertEquals(option, clonedOption);
        assertTrue(clonedOption.getValues().isEmpty());
    }

    @Test
    public void testCloneWithProcessValue() {
        // When
        option.processValue("3");
        Option clonedOption = (Option) option.clone();

        // Then
        assertNotSame(option, clonedOption);
        assertEquals(option, clonedOption);
        assertEquals(3, clonedOption.getValues().size());
    }
}
