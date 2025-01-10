
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class LeafDefault_entryTest {

    @Test
    public void testEntry() {
        // Given
        Entry<String, Rectangle> entry1 = new Entry<String, Rectangle>() {
            @Override
            public String value() {
                return "Entry1";
            }

            @Override
            public Rectangle geometry() {
                return Rectangle.create(0, 0, 1, 1);
            }
        };

        Entry<String, Rectangle> entry2 = new Entry<String, Rectangle>() {
            @Override
            public String value() {
                return "Entry2";
            }

            @Override
            public Rectangle geometry() {
                return Rectangle.create(1, 1, 2, 2);
            }
        };

        List<Entry<String, Rectangle>> entries = Arrays.asList(entry1, entry2);
        Context<String, Rectangle> context = new Context<String, Rectangle>() {};
        LeafDefault<String, Rectangle> leaf = new LeafDefault<>(entries, context);

        // When
        Entry<String, Rectangle> result = leaf.entry(1);

        // Then
        assertEquals("Entry2", result.value());
        assertEquals(Rectangle.create(1, 1, 2, 2), result.geometry());
    }
}
