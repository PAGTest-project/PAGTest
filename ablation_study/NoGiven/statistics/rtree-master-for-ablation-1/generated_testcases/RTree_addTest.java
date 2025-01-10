
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.internal.NodeAndEntries;

@RunWith(MockitoJUnitRunner.class)
public class RTree_addTest {

    @Mock
    private Node<String, Geometry> mockNode;

    @Mock
    private Context<String, Geometry> mockContext;

    @Mock
    private Factory<String, Geometry> mockFactory;

    @Mock
    private Entry<String, Geometry> mockEntry;

    @Test
    public void testAddWhenRootIsPresent() {
        // Given
        RTree<String, Geometry> tree = new RTree<>(Optional.of(mockNode), 1, mockContext);
        when(mockNode.add(any())).thenReturn(List.of(mockNode));
        when(mockContext.factory()).thenReturn(mockFactory);
        when(mockFactory.createNonLeaf(any(), any())).thenReturn(mockNode);

        // When
        RTree<String, Geometry> result = tree.add(mockEntry);

        // Then
        assertEquals(2, result.size());
        assertEquals(mockNode, result.root().get());
    }

    @Test
    public void testAddWhenRootIsNotPresent() {
        // Given
        RTree<String, Geometry> tree = new RTree<>(Optional.empty(), 0, mockContext);
        when(mockContext.factory()).thenReturn(mockFactory);
        when(mockFactory.createLeaf(any(), any())).thenReturn(mockNode);

        // When
        RTree<String, Geometry> result = tree.add(mockEntry);

        // Then
        assertEquals(1, result.size());
        assertEquals(mockNode, result.root().get());
    }
}
