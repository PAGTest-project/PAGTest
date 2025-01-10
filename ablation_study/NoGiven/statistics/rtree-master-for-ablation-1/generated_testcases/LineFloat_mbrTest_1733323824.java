
package com.github.davidmoten.rtree.geometry.internal;

import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineFloat_mbrTest {

    @Test
    public void testMbr() {
        LineFloat line = LineFloat.create(1.0, 2.0, 3.0, 4.0);
        Rectangle mbr = line.mbr();
        assertEquals(1.0, mbr.x1(), 0.0);
        assertEquals(2.0, mbr.y1(), 0.0);
        assertEquals(3.0, mbr.x2(), 0.0);
        assertEquals(4.0, mbr.y2(), 0.0);
    }
}
