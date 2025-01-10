
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_equalsTest {

    private Option option1;
    private Option option2;
    private Option option3;

    @BeforeEach
    public void setUp() {
        option1 = new Option("opt1", "description1");
        option2 = new Option("opt2", "description2");
        option3 = new Option("opt1", "description1");
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(option1.equals(option1));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(option1.equals("notAnOption"));
    }

    @Test
    public void testEqualsDifferentOptions() {
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsSameOptions() {
        assertTrue(option1.equals(option3));
    }
}
