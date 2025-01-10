
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;

public class RTree_asStringTest {

    @Test
    public void testAsStringWithEmptyRoot() {
        // Given
        RTree<Object, Geometry> tree = new RTree<>(Optional.empty(), 0, null);

        // When
        String result = tree.asString();

        // Then
        assertEquals("", result);
    }

    @Test
    public void testAsStringWithNonEmptyRoot() {
        // Given
        Node<Object, Geometry> mockNode = mock(Node.class);
        when(mockNode.geometry()).thenReturn(mock(Geometry.class));
        RTree<Object, Geometry> tree = new RTree<>(Optional.of(mockNode), 1, null);

        // When
        String result = tree.asString();

        // Then
        assertEquals("mbr=" + mockNode.geometry() + "\n", result);
    }
}
