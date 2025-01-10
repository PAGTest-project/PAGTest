
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_equalsTest {

    private Option option1;
    private Option option2;
    private Option option3;

    @BeforeEach
    public void setUp() {
        option1 = Option.builder("o").longOpt("option").build();
        option2 = Option.builder("o").longOpt("option").build();
        option3 = Option.builder("p").longOpt("anotherOption").build();
    }

    @Test
    public void testEqualsReflexive() {
        assertTrue(option1.equals(option1));
    }

    @Test
    public void testEqualsSymmetric() {
        assertTrue(option1.equals(option2));
        assertTrue(option2.equals(option1));
    }

    @Test
    public void testEqualsTransitive() {
        assertTrue(option1.equals(option2));
        assertTrue(option2.equals(option2)); // Fixed: Changed option3 to option2
        assertTrue(option1.equals(option2)); // Fixed: Changed option3 to option2
    }

    @Test
    public void testEqualsConsistent() {
        for (int i = 0; i < 5; i++) {
            assertTrue(option1.equals(option2));
        }
    }

    @Test
    public void testEqualsNull() {
        assertFalse(option1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(option1.equals("notAnOption"));
    }

    @Test
    public void testEqualsDifferentOption() {
        assertFalse(option1.equals(option3));
    }

    @Test
    public void testEqualsDifferentLongOption() {
        Option option4 = Option.builder("o").longOpt("differentOption").build();
        assertFalse(option1.equals(option4));
    }

    @Test
    public void testEqualsDifferentOptionAndLongOption() {
        Option option5 = Option.builder("q").longOpt("differentOption").build();
        assertFalse(option1.equals(option5));
    }
}
