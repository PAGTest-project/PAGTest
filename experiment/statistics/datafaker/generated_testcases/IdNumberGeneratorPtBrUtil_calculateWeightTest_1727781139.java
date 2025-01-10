
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdNumberGeneratorPtBrUtil_calculateWeightTest {

    @Test
    public void testCalculateWeight() {
        String num = "1234567890";
        int weight = 10;
        int start = 0;
        int end = 5;

        int expectedSum = (1 * 10) + (2 * 9) + (3 * 8) + (4 * 7) + (5 * 6);
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }
}
