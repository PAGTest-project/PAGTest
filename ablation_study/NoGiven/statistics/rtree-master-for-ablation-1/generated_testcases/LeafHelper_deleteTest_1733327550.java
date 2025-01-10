
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Leaf;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;

public class LeafHelper_deleteTest {

    @Mock
    private Leaf<String, Geometry> leaf;

    @Mock
    private Context<String, Geometry> context;

    @Mock
    private Entry<String, Geometry> entry;

    @Mock
    private Context.Factory<String, Geometry> factory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(leaf.context()).thenReturn(context);
        when(context.minChildren()).thenReturn(2);
        when(context.factory()).thenReturn(factory);
    }

    @Test
    public void testDeleteEntryNotPresent() {
        when(leaf.entries()).thenReturn(Collections.emptyList());

        NodeAndEntries<String, Geometry> result = LeafHelper.delete(entry, false, leaf);

        assertEquals(Optional.of(leaf), result.node());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
        assertEquals(0, result.countDeleted());
    }

    @Test
    public void testDeleteEntryPresentOnce() {
        when(leaf.entries()).thenReturn(Arrays.asList(entry));
        when(factory.createLeaf(anyList(), eq(context))).thenReturn(leaf);

        NodeAndEntries<String, Geometry> result = LeafHelper.delete(entry, false, leaf);

        assertEquals(Optional.of(leaf), result.node());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
        assertEquals(1, result.countDeleted());
    }

    @Test
    public void testDeleteEntryPresentMultipleTimes() {
        when(leaf.entries()).thenReturn(Arrays.asList(entry, entry, entry));
        when(factory.createLeaf(anyList(), eq(context))).thenReturn(leaf);

        NodeAndEntries<String, Geometry> result = LeafHelper.delete(entry, true, leaf);

        assertEquals(Optional.of(leaf), result.node());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
        assertEquals(3, result.countDeleted());
    }

    @Test
    public void testDeleteEntryPresentButNotEnoughChildren() {
        when(leaf.entries()).thenReturn(Arrays.asList(entry));
        when(factory.createLeaf(anyList(), eq(context))).thenReturn(leaf);
        when(context.minChildren()).thenReturn(2);

        NodeAndEntries<String, Geometry> result = LeafHelper.delete(entry, false, leaf);

        assertEquals(Optional.empty(), result.node());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
        assertEquals(1, result.countDeleted());
    }
}
