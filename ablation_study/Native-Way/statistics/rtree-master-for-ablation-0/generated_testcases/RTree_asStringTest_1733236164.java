
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;

public class RTree_asStringTest {

    @Test
    public void testAsStringWithRootPresent() {
        Node<Object, Geometry> mockNode = mock(Node.class);
        RTree<Object, Geometry> tree = new RTree<>(mockNode, 1, null);
        when(tree.asString(mockNode, "")).thenReturn("mockString");

        String result = tree.asString();
        assertEquals("mockString", result);
    }

    @Test
    public void testAsStringWithRootNotPresent() {
        RTree<Object, Geometry> tree = new RTree<>(Optional.empty(), 0, null);

        String result = tree.asString();
        assertEquals("", result);
    }
}
