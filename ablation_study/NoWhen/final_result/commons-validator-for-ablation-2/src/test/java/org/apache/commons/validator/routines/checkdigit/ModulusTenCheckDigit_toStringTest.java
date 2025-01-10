
package org.apache.commons.validator.routines.checkdigit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModulusTenCheckDigit_toStringTest {

    @Test
    public void testToString() {
        int[] positionWeight = {1, 2, 3};
        boolean useRightPos = true;
        boolean sumWeightedDigits = false;

        ModulusTenCheckDigit checkDigit = new ModulusTenCheckDigit(positionWeight, useRightPos, sumWeightedDigits);

        String expected = "ModulusTenCheckDigit[postitionWeight=[1, 2, 3], useRightPos=true, sumWeightedDigits=false]";
        assertEquals(expected, checkDigit.toString());
    }
}
