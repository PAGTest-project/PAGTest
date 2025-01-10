
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_calculateWeightTest {

    @Test
    public void testCalculateWeightSimple() {
        String num = "123456789012";
        int weight = 9;
        int start = 4;
        int end = 12;
        int expected = 180; // Calculated manually for "56789012" with weight 9
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    public void testCalculateWeightWithZeroWeight() {
        String num = "123456789012";
        int weight = 0;
        int start = 4;
        int end = 12;
        int expected = 0; // Any number multiplied by zero is zero
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    public void testCalculateWeightWithSingleDigit() {
        String num = "1";
        int weight = 10;
        int start = 0;
        int end = 1;
        int expected = 10; // Only one digit '1' with weight 10
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    public void testCalculateWeightWithFullRange() {
        String num = "123456789012";
        int weight = 10;
        int start = 0;
        int end = 12;
        int expected = 210; // Calculated manually for "123456789012" with weight 10
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }

    @Test
    public void testCalculateWeightWithNegativeWeight() {
        String num = "123456789012";
        int weight = -1;
        int start = 4;
        int end = 12;
        int expected = -180; // Calculated manually for "56789012" with weight -1
        assertEquals(expected, IdNumberGeneratorPtBrUtil.calculateWeight(num, weight, start, end));
    }
}
