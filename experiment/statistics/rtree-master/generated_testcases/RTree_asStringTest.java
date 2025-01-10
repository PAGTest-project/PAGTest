
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;

public class RTree_asStringTest {

    @Test
    public void testAsStringWithEmptyRoot() {
        RTree<Object, Geometry> tree = new RTree<>(Optional.empty(), 0, null);
        assertEquals("", tree.asString());
    }

    @Test
    public void testAsStringWithNonEmptyRoot() {
        Node<Object, Geometry> mockNode = mock(Node.class);
        RTree<Object, Geometry> tree = new RTree<>(Optional.of(mockNode), 1, null);
        when(mockNode.asString("")).thenReturn("mockString");
        assertEquals("mockString", tree.asString());
    }
}
