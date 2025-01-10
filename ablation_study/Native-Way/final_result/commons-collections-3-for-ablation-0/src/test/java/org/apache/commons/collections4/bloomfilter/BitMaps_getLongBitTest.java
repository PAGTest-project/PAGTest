
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BitMaps_getLongBitTest {

    @Test
    public void testGetLongBitPositiveIndex() {
        assertEquals(1L << 0, BitMaps.getLongBit(0), "Bit index 0");
        assertEquals(1L << 1, BitMaps.getLongBit(1), "Bit index 1");
        assertEquals(1L << 31, BitMaps.getLongBit(31), "Bit index 31");
        assertEquals(1L << 63, BitMaps.getLongBit(63), "Bit index 63");
    }

    @Test
    public void testGetLongBitNegativeIndex() {
        // Negative index behavior is not defined, but we can test the bitwise shift result
        assertEquals(1L << -1, BitMaps.getLongBit(-1), "Bit index -1");
        assertEquals(1L << -32, BitMaps.getLongBit(-32), "Bit index -32");
    }

    @Test
    public void testGetLongBitLargeIndex() {
        // Large index behavior is not defined, but we can test the bitwise shift result
        assertEquals(1L << 64, BitMaps.getLongBit(64), "Bit index 64");
        assertEquals(1L << 128, BitMaps.getLongBit(128), "Bit index 128");
    }
}
