
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
        option1 = Option.builder("opt1").longOpt("longOpt1").build();
        option2 = Option.builder("opt2").longOpt("longOpt2").build();
        option3 = Option.builder("opt1").longOpt("longOpt1").build();
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(option1, option1);
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(option1.equals("notAnOption"));
    }

    @Test
    public void testEqualsDifferentOptions() {
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsSameOptions() {
        assertEquals(option1, option3);
    }

    @Test
    public void testEqualsNull() {
        assertFalse(option1.equals(null));
    }
}
