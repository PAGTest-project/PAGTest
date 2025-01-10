
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModulusTenCheckDigit_toStringTest {

    private ModulusTenCheckDigit routine;

    @BeforeEach
    protected void setUp() {
        routine = new ModulusTenCheckDigit(new int[] { 1, 2 }, true, true);
    }

    @Test
    public void testToStringWithRightPosAndSumWeightedDigits() {
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2], useRightPos=true, sumWeightedDigits=true]";
        assertEquals(expected, routine.toString());
    }

    @Test
    public void testToStringWithLeftPosAndNoSumWeightedDigits() {
        routine = new ModulusTenCheckDigit(new int[] { 3, 4 }, false, false);
        String expected = "ModulusTenCheckDigit[postitionWeight=[3, 4], useRightPos=false, sumWeightedDigits=false]";
        assertEquals(expected, routine.toString());
    }

    @Test
    public void testToStringWithEmptyPositionWeight() {
        routine = new ModulusTenCheckDigit(new int[] {}, false, false);
        String expected = "ModulusTenCheckDigit[postitionWeight=[], useRightPos=false, sumWeightedDigits=false]";
        assertEquals(expected, routine.toString());
    }
}
