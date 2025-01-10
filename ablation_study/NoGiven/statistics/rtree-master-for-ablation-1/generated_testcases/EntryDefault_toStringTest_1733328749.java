
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntryDefault_toStringTest {

    @Test
    public void testToString() {
        EntryDefault<String, Geometry> entry = new EntryDefault<>("testValue", new Geometry() {
            @Override
            public Geometry buffer(double distance) {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public double distance(Geometry geometry) {
                return 0;
            }

            @Override
            public boolean hasGeometry(Geometry geometry) {
                return false;
            }

            @Override
            public boolean intersects(Geometry geometry) {
                return false;
            }

            @Override
            public boolean contains(Geometry geometry) {
                return false;
            }

            @Override
            public boolean isDoublePrecision() {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }

            @Override
            public String toString() {
                return "testGeometry";
            }
        });

        assertEquals("Entry [value=testValue, geometry=testGeometry]", entry.toString());
    }
}
