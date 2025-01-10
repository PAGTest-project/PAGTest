
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Rectangle;

public class RTree_asStringTest {

    @Test
    public void testAsStringWithRootPresent() {
        // Given
        Node<Object, Rectangle> mockNode = mock(Node.class);
        when(mockNode.geometry()).thenReturn(Rectangle.create(0, 0, 1, 1));
        RTree<Object, Rectangle> tree = new RTree<>(mockNode, 1, null);

        // When
        String result = tree.asString();

        // Then
        assertEquals("mbr=Rectangle [x1=0.0, y1=0.0, x2=1.0, y2=1.0]\n", result);
    }

    @Test
    public void testAsStringWithRootNotPresent() {
        // Given
        RTree<Object, Rectangle> tree = new RTree<>(Optional.empty(), 0, null);

        // When
        String result = tree.asString();

        // Then
        assertEquals("", result);
    }
}
