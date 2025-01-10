
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
        option1 = Option.builder("o").longOpt("option").hasArg().build();
        option2 = Option.builder("o").longOpt("option").hasArg().build();
        option3 = Option.builder("p").longOpt("anotherOption").hasArg().build();
    }

    @Test
    public void testEqualsReflexive() {
        assertEquals(option1, option1);
    }

    @Test
    public void testEqualsSymmetric() {
        assertEquals(option1, option2);
        assertEquals(option2, option1);
    }

    @Test
    public void testEqualsTransitive() {
        assertEquals(option1, option2);
        assertEquals(option2, option1);
        assertEquals(option1, option1);
    }

    @Test
    public void testEqualsWithDifferentOptions() {
        assertNotEquals(option1, option3);
    }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(option1, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertNotEquals(option1, new Object());
    }

    @Test
    public void testEqualsWithProcessedValues() {
        option1.processValue("value1");
        option2.processValue("value1");
        assertEquals(option1, option2);
    }

    @Test
    public void testEqualsWithDifferentProcessedValues() {
        option1.processValue("value1");
        option2.processValue("value2");
        assertNotEquals(option1, option2);
    }

    @Test
    public void testEqualsWithClearedValues() {
        option1.processValue("value1");
        option2.processValue("value1");
        option1.clearValues();
        option2.clearValues();
        assertEquals(option1, option2);
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testHashCodeInconsistency() {
        assertNotEquals(option1.hashCode(), option3.hashCode());
    }

    @Test
    public void testHashCodeWithProcessedValues() {
        option1.processValue("value1");
        option2.processValue("value1");
        assertEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentProcessedValues() {
        option1.processValue("value1");
        option2.processValue("value2");
        assertNotEquals(option1.hashCode(), option2.hashCode());
    }

    @Test
    public void testHashCodeWithClearedValues() {
        option1.processValue("value1");
        option2.processValue("value1");
        option1.clearValues();
        option2.clearValues();
        assertEquals(option1.hashCode(), option2.hashCode());
    }
}
