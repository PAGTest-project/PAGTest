
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BitMaps_getLongIndexTest {

    @Test
    public void testGetLongIndexPositive() {
        assertEquals(0, BitMaps.getLongIndex(0));
        assertEquals(0, BitMaps.getLongIndex(63));
        assertEquals(1, BitMaps.getLongIndex(64));
        assertEquals(1, BitMaps.getLongIndex(127));
        assertEquals(2, BitMaps.getLongIndex(128));
    }

    @Test
    public void testGetLongIndexNegative() {
        assertEquals(-1, BitMaps.getLongIndex(-1));
        assertEquals(-1, BitMaps.getLongIndex(-64));
        assertEquals(-2, BitMaps.getLongIndex(-65));
        assertEquals(-2, BitMaps.getLongIndex(-128));
    }
}
