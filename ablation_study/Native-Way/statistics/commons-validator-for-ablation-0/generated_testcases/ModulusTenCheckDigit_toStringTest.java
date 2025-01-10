
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModulusTenCheckDigit_toStringTest {

    private ModulusTenCheckDigit checkDigit;

    @BeforeEach
    public void setUp() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 1, 3, 1, 7, 3, 9, 1 }, false, false);
    }

    @Test
    public void testToStringDefault() {
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 3, 1, 7, 3, 9, 1], useRightPos=false, sumWeightedDigits=false]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringUseRightPos() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 1, 2 }, true, false);
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2], useRightPos=true, sumWeightedDigits=false]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringSumWeightedDigits() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 1, 2 }, false, true);
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2], useRightPos=false, sumWeightedDigits=true]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringAllTrue() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 1, 2 }, true, true);
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2], useRightPos=true, sumWeightedDigits=true]";
        assertEquals(expected, checkDigit.toString());
    }
}
