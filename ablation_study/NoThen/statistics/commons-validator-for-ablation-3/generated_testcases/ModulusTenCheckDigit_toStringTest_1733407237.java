
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModulusTenCheckDigit_toStringTest {

    private ModulusTenCheckDigit checkDigit;

    @BeforeEach
    public void setUp() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 1, 2 }, true, true);
    }

    @Test
    public void testToStringWithRightPosAndSumWeightedDigits() {
        String expected = "ModulusTenCheckDigit[positionWeight=[1, 2], useRightPos=true, sumWeightedDigits=true]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringWithLeftPosAndNoSumWeightedDigits() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 3, 4 }, false, false);
        String expected = "ModulusTenCheckDigit[positionWeight=[3, 4], useRightPos=false, sumWeightedDigits=false]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringWithRightPosAndNoSumWeightedDigits() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 5, 6 }, true, false);
        String expected = "ModulusTenCheckDigit[positionWeight=[5, 6], useRightPos=true, sumWeightedDigits=false]";
        assertEquals(expected, checkDigit.toString());
    }

    @Test
    public void testToStringWithLeftPosAndSumWeightedDigits() {
        checkDigit = new ModulusTenCheckDigit(new int[] { 7, 8 }, false, true);
        String expected = "ModulusTenCheckDigit[positionWeight=[7, 8], useRightPos=false, sumWeightedDigits=true]";
        assertEquals(expected, checkDigit.toString());
    }
}
