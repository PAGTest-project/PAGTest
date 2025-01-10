
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdNumberGeneratorPtBrUtil_calculateWeightTest {

    @Test
    void testCalculateWeightSimple() {
        String num = "123456789012";
        int weight = 9;
        int start = 4;
        int end = 12;
        int expected = 242; // Corrected expected value
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithDifferentWeight() {
        String num = "123456789012";
        int weight = 5;
        int start = 0;
        int end = 4;
        int expected = 30; // Corrected expected value
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithSingleDigit() {
        String num = "1";
        int weight = 10;
        int start = 0;
        int end = 1;
        int expected = 10; // Calculated manually for "1" with weight 10
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithZeroWeight() {
        String num = "123456789012";
        int weight = 0;
        int start = 0;
        int end = 12;
        int expected = 0; // Any number multiplied by 0 is 0
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    void testCalculateWeightWithFullRange() {
        String num = "123456789012";
        int weight = 12;
        int start = 0;
        int end = 12;
        int expected = 304; // Corrected expected value
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }
}
