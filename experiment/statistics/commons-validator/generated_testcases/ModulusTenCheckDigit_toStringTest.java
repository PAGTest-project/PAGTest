
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
    public void testToString() {
        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2], useRightPos=true, sumWeightedDigits=true]";
        assertEquals(expected, checkDigit.toString());
    }
}
