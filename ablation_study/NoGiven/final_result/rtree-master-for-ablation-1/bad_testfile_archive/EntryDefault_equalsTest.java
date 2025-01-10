
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.internal.util.ObjectsHelper;
import org.junit.Test;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EntryDefault_equalsTest {

    @Test
    public void testEquals_SameValues() {
        Point geometry = mock(Point.class);
        EntryDefault<String, Point> entry1 = new EntryDefault<>("value", geometry);
        EntryDefault<String, Point> entry2 = new EntryDefault<>("value", geometry);

        assertTrue(entry1.equals(entry2));
    }

    @Test
    public void testEquals_DifferentValues() {
        Point geometry = mock(Point.class);
        EntryDefault<String, Point> entry1 = new EntryDefault<>("value1", geometry);
        EntryDefault<String, Point> entry2 = new EntryDefault<>("value2", geometry);

        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void testEquals_DifferentGeometry() {
        Point geometry1 = mock(Point.class);
        Point geometry2 = mock(Point.class);
        EntryDefault<String, Point> entry1 = new EntryDefault<>("value", geometry1);
        EntryDefault<String, Point> entry2 = new EntryDefault<>("value", geometry2);

        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void testEquals_NullGeometry() {
        EntryDefault<String, Point> entry1 = new EntryDefault<>("value", null);
        EntryDefault<String, Point> entry2 = new EntryDefault<>("value", null);

        assertTrue(entry1.equals(entry2));
    }

    @Test
    public void testEquals_DifferentClass() {
        Point geometry = mock(Point.class);
        EntryDefault<String, Point> entry = new EntryDefault<>("value", geometry);
        Object obj = new Object();

        assertFalse(entry.equals(obj));
    }

    @Test
    public void testEquals_NullObject() {
        Point geometry = mock(Point.class);
        EntryDefault<String, Point> entry = new EntryDefault<>("value", geometry);

        assertFalse(entry.equals(null));
    }
}
