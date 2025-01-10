
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class SelectorMinimalOverlapArea_selectTest {

    @Test
    public void testSelect() {
        // Given
        Geometry geometry = mock(Geometry.class);
        Rectangle mbr = mock(Rectangle.class);
        when(geometry.mbr()).thenReturn(mbr);

        Node<Object, Geometry> node1 = mock(Node.class);
        Node<Object, Geometry> node2 = mock(Node.class);
        List<Node<Object, Geometry>> nodes = Arrays.asList(node1, node2);

        // When
        SelectorMinimalOverlapArea selector = new SelectorMinimalOverlapArea();
        Node<Object, Geometry> selectedNode = selector.select(geometry, nodes);

        // Then
        assertEquals(node1, selectedNode);
    }
}
