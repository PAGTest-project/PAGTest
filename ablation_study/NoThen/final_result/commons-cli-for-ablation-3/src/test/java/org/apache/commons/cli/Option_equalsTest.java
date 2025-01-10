
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_equalsTest {

    private Option option1;
    private Option option2;

    @BeforeEach
    public void setUp() {
        option1 = Option.builder("opt1").longOpt("longOpt1").build();
        option2 = Option.builder("opt2").longOpt("longOpt2").build();
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(option1.equals(option1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(option1.equals("not an Option"));
    }

    @Test
    public void testEqualsDifferentShortOption() {
        Option option3 = Option.builder("opt3").longOpt("longOpt1").build();
        assertFalse(option1.equals(option3));
    }

    @Test
    public void testEqualsDifferentLongOption() {
        Option option3 = Option.builder("opt1").longOpt("longOpt3").build();
        assertFalse(option1.equals(option3));
    }

    @Test
    public void testEqualsSameOptions() {
        Option option3 = Option.builder("opt1").longOpt("longOpt1").build();
        assertTrue(option1.equals(option3));
    }
}
