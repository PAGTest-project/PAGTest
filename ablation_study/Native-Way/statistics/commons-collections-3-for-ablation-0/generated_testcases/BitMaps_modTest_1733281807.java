
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BitMaps_modTest {

    @Test
    public void testModWithPositiveDividendAndDivisor() {
        assertEquals(0, BitMaps.mod(0, 1), "0 % 1 should be 0");
        assertEquals(1, BitMaps.mod(1, 2), "1 % 2 should be 1");
        assertEquals(2, BitMaps.mod(10, 4), "10 % 4 should be 2");
        assertEquals(3, BitMaps.mod(15, 4), "15 % 4 should be 3");
    }

    @Test
    public void testModWithLargeDividend() {
        assertEquals(0, BitMaps.mod(Long.MAX_VALUE, 1), "Long.MAX_VALUE % 1 should be 0");
        assertEquals(1, BitMaps.mod(Long.MAX_VALUE, 2), "Long.MAX_VALUE % 2 should be 1");
        assertEquals(3, BitMaps.mod(Long.MAX_VALUE, 4), "Long.MAX_VALUE % 4 should be 3");
    }

    @Test
    public void testModWithNegativeDividend() {
        assertEquals(0, BitMaps.mod(-1, 1), "-1 % 1 should be 0");
        assertEquals(1, BitMaps.mod(-1, 2), "-1 % 2 should be 1");
        assertEquals(2, BitMaps.mod(-10, 4), "-10 % 4 should be 2");
        assertEquals(3, BitMaps.mod(-15, 4), "-15 % 4 should be 3");
    }

    @Test
    public void testModWithZeroDividend() {
        assertEquals(0, BitMaps.mod(0, 1), "0 % 1 should be 0");
        assertEquals(0, BitMaps.mod(0, 2), "0 % 2 should be 0");
        assertEquals(0, BitMaps.mod(0, 4), "0 % 4 should be 0");
    }

    @Test
    public void testModWithDivisorGreaterThanDividend() {
        assertEquals(10, BitMaps.mod(10, 11), "10 % 11 should be 10");
        assertEquals(15, BitMaps.mod(15, 16), "15 % 16 should be 15");
        assertEquals(20, BitMaps.mod(20, 21), "20 % 21 should be 20");
    }
}
