
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_calculateWeightTest {

    @Test
    void testCalculateWeightSimple() {
        String num = "12345678";
        int weight = 9;
        int start = 0;
        int end = 8;
        int expectedSum = 1*9 + 2*8 + 3*7 + 4*6 + 5*5 + 6*4 + 7*3 + 8*2;
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithDifferentStartEnd() {
        String num = "12345678";
        int weight = 7;
        int start = 2;
        int end = 6;
        int expectedSum = 3*7 + 4*6 + 5*5 + 6*4;
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithSingleCharacter() {
        String num = "5";
        int weight = 3;
        int start = 0;
        int end = 1;
        int expectedSum = 5*3;
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithZeroWeight() {
        String num = "12345678";
        int weight = 0;
        int start = 0;
        int end = 8;
        int expectedSum = 0;
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithNegativeWeight() {
        String num = "12345678";
        int weight = -1;
        int start = 0;
        int end = 8;
        int expectedSum = 1*(-1) + 2*(-2) + 3*(-3) + 4*(-4) + 5*(-5) + 6*(-6) + 7*(-7) + 8*(-8);
        assertEquals(expectedSum, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }
}
