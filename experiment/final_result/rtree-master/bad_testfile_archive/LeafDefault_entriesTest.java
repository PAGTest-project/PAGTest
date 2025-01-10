
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeafDefault_entriesTest {

    @Test
    public void testEntries() {
        // Given
        Entry<String, Rectangle> entry1 = new Entry<String, Rectangle>() {
            @Override
            public String value() {
                return "Entry1";
            }

            @Override
            public Rectangle geometry() {
                return Rectangle.create(0.0, 0.0, 1.0, 1.0);
            }
        };

        Entry<String, Rectangle> entry2 = new Entry<String, Rectangle>() {
            @Override
            public String value() {
                return "Entry2";
            }

            @Override
            public Rectangle geometry() {
                return Rectangle.create(2.0, 2.0, 3.0, 3.0);
            }
        };

        List<Entry<String, Rectangle>> entries = Arrays.asList(entry1, entry2);
        Context<String, Rectangle> context = new Context<String, Rectangle>() {
            // Dummy implementation for test
        };

        LeafDefault<String, Rectangle> leaf = new LeafDefault<>(entries, context);

        // When
        List<Entry<String, Rectangle>> result = leaf.entries();

        // Then
        assertEquals(entries, result);
    }
}
