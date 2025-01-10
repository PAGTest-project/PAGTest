
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableRangeSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangeExtent_toStringTest {

    @Test
    public void testToStringUnbounded() {
        Type mockType = new Type() {
            @Override
            public String toString() {
                return "MockType";
            }
        };

        RangeExtent rangeExtent = new RangeExtent(null, mockType, ImmutableMap.of());
        assertEquals("MockType", rangeExtent.toString());
    }

    @Test
    public void testToStringBounded() {
        Type mockType = new Type() {
            @Override
            public String toString() {
                return "MockType";
            }
        };

        ImmutableRangeSet<Integer> mockRangeSet = ImmutableRangeSet.of();
        RangeExtent rangeExtent = new RangeExtent(null, mockType, ImmutableMap.of("path", mockRangeSet));
        assertEquals("MockType {path=[[]]}", rangeExtent.toString());
    }
}
