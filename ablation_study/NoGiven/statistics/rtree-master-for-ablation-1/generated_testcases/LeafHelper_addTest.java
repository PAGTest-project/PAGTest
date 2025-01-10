
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Leaf;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import org.junit.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LeafHelper_addTest {

    @Test
    public void testAddWhenEntriesSizeLessThanMaxChildren() {
        // Given
        Entry<String, Geometry> entry = mock(Entry.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);
        Context<String, Geometry> context = mock(Context.class);
        List<Entry<String, Geometry>> entries = Collections.emptyList();

        when(leaf.entries()).thenReturn(entries);
        when(leaf.context()).thenReturn(context);
        when(context.maxChildren()).thenReturn(5);
        when(context.factory()).thenReturn(mock(Context.Factory.class));
        when(context.factory().createLeaf(anyList(), eq(context))).thenReturn(mock(Leaf.class));

        // When
        List<Node<String, Geometry>> result = LeafHelper.add(entry, leaf);

        // Then
        assertEquals(1, result.size());
    }

    @Test
    public void testAddWhenEntriesSizeGreaterThanMaxChildren() {
        // Given
        Entry<String, Geometry> entry = mock(Entry.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);
        Context<String, Geometry> context = mock(Context.class);
        List<Entry<String, Geometry>> entries = Collections.nCopies(6, mock(Entry.class));

        when(leaf.entries()).thenReturn(entries);
        when(leaf.context()).thenReturn(context);
        when(context.maxChildren()).thenReturn(5);
        when(context.minChildren()).thenReturn(2);
        when(context.splitter()).thenReturn(mock(Context.Splitter.class));
        when(context.splitter().split(anyList(), anyInt())).thenReturn(mock(ListPair.class));
        when(context.factory()).thenReturn(mock(Context.Factory.class));
        when(context.factory().createLeaf(anyList(), eq(context))).thenReturn(mock(Leaf.class));

        // When
        List<Node<String, Geometry>> result = LeafHelper.add(entry, leaf);

        // Then
        assertEquals(2, result.size());
    }
}
