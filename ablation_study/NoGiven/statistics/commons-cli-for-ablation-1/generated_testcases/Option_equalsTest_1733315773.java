
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
        option1 = Option.builder("o").longOpt("option").build();
        option2 = Option.builder("o").longOpt("option").build();
        option3 = Option.builder("p").longOpt("param").build();
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
        assertTrue(option2.equals(option3));
        assertTrue(option1.equals(option3));
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
        assertFalse(option1.equals("not an option"));
    }

    @Test
    public void testEqualsDifferentOption() {
        assertFalse(option1.equals(option3));
    }

    @Test
    public void testEqualsHashCodeConsistency() {
        assertTrue(option1.equals(option2));
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testEqualsDifferentHashCode() {
        assertFalse(option1.equals(option3));
        assertFalse(option1.hashCode() == option3.hashCode());
    }

    @Test
    public void testEqualsAfterClearValues() {
        option1.processValue("value1");
        option2.processValue("value1");
        option1.clearValues();
        option2.clearValues();
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEqualsAfterProcessValue() {
        option1.processValue("value1");
        option2.processValue("value1");
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEqualsDifferentValues() {
        option1.processValue("value1");
        option2.processValue("value2");
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEqualsDifferentOpt() {
        Option opt1 = Option.builder("o1").build();
        Option opt2 = Option.builder("o2").build();
        assertFalse(opt1.equals(opt2));
    }

    @Test
    public void testEqualsDifferentLongOpt() {
        Option longOpt1 = Option.builder("o").longOpt("long1").build();
        Option longOpt2 = Option.builder("o").longOpt("long2").build();
        assertFalse(longOpt1.equals(longOpt2));
    }
}
