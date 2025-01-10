
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.NonLeaf;
import com.github.davidmoten.rtree.geometry.Geometry;

public class NonLeafHelper_deleteTest {

    @Mock
    private Entry<String, Geometry> entry;
    @Mock
    private NonLeaf<String, Geometry> node;
    @Mock
    private Node<String, Geometry> child;
    @Mock
    private Context<String, Geometry> context;
    @Mock
    private NodeAndEntries<String, Geometry> nodeAndEntries;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(node.context()).thenReturn(context);
        when(node.children()).thenReturn(Collections.singletonList(child));
        when(entry.geometry()).thenReturn(mock(Geometry.class));
        when(child.geometry()).thenReturn(mock(Geometry.class));
        when(child.delete(any(), anyBoolean())).thenReturn(nodeAndEntries);
        when(nodeAndEntries.node()).thenReturn(Optional.of(child));
        when(nodeAndEntries.entriesToAdd()).thenReturn(Collections.emptyList());
        when(nodeAndEntries.countDeleted()).thenReturn(0);
    }

    @Test
    public void testDeleteNoIntersection() {
        when(entry.geometry().intersects(any())).thenReturn(false);
        NodeAndEntries<String, Geometry> result = NonLeafHelper.delete(entry, true, node);
        assertEquals(node, result.node().get());
        assertEquals(0, result.countDeleted());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
    }

    @Test
    public void testDeleteWithIntersectionNoDeletion() {
        when(entry.geometry().intersects(any())).thenReturn(true);
        when(nodeAndEntries.node()).thenReturn(Optional.of(child));
        NodeAndEntries<String, Geometry> result = NonLeafHelper.delete(entry, true, node);
        assertEquals(node, result.node().get());
        assertEquals(0, result.countDeleted());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
    }

    @Test
    public void testDeleteWithIntersectionAndDeletion() {
        when(entry.geometry().intersects(any())).thenReturn(true);
        Node<String, Geometry> newNode = mock(Node.class);
        when(nodeAndEntries.node()).thenReturn(Optional.of(newNode));
        when(nodeAndEntries.countDeleted()).thenReturn(1);
        NodeAndEntries<String, Geometry> result = NonLeafHelper.delete(entry, true, node);
        assertNotEquals(node, result.node().get());
        assertEquals(1, result.countDeleted());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
    }

    @Test
    public void testDeleteWithIntersectionAndDeletionBelowMinChildren() {
        when(entry.geometry().intersects(any())).thenReturn(true);
        when(nodeAndEntries.node()).thenReturn(Optional.empty());
        when(nodeAndEntries.countDeleted()).thenReturn(1);
        NodeAndEntries<String, Geometry> result = NonLeafHelper.delete(entry, true, node);
        assertFalse(result.node().isPresent());
        assertEquals(1, result.countDeleted());
        assertEquals(Collections.emptyList(), result.entriesToAdd());
    }
}
