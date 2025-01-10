
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Flags_equalsTest {

    @Test
    public void testEqualsWithSameObject() {
        Flags flags = new Flags(1);
        assertTrue(flags.equals(flags));
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        Flags flags = new Flags(1);
        assertFalse(flags.equals("Not a Flags object"));
    }

    @Test
    public void testEqualsWithDifferentFlags() {
        Flags flags1 = new Flags(1);
        Flags flags2 = new Flags(2);
        assertFalse(flags1.equals(flags2));
    }

    @Test
    public void testEqualsWithSameFlags() {
        Flags flags1 = new Flags(1);
        Flags flags2 = new Flags(1);
        assertTrue(flags1.equals(flags2));
    }

    @Test
    public void testEqualsAfterStateChange() {
        Flags flags1 = new Flags(1);
        Flags flags2 = new Flags(1);
        flags1.turnOn(2);
        flags2.turnOn(2);
        assertTrue(flags1.equals(flags2));
    }

    @Test
    public void testEqualsAfterDifferentStateChange() {
        Flags flags1 = new Flags(1);
        Flags flags2 = new Flags(1);
        flags1.turnOn(2);
        flags2.turnOff(1);
        assertFalse(flags1.equals(flags2));
    }
}
