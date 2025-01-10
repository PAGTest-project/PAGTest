
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.davidmoten.guavamini.Lists;
import com.github.davidmoten.rtree.geometry.Geometry;

public class RTree_addTest {

    @Mock
    private Node<String, Geometry> mockNode;

    @Mock
    private Context<String, Geometry> mockContext;

    @Mock
    private Factory<String, Geometry> mockFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockContext.factory()).thenReturn(mockFactory);
    }

    @Test
    public void testAddWhenRootIsPresent() {
        // Given
        RTree<String, Geometry> tree = new RTree<>(Optional.of(mockNode), 1, mockContext);
        Entry<String, Geometry> entry = mock(Entry.class);
        List<Node<String, Geometry>> nodes = Lists.newArrayList(mockNode);
        when(mockNode.add(entry)).thenReturn(nodes);

        // When
        RTree<String, Geometry> result = tree.add(entry);

        // Then
        assertEquals(2, result.size());
        verify(mockNode).add(entry);
    }

    @Test
    public void testAddWhenRootIsNotPresent() {
        // Given
        RTree<String, Geometry> tree = new RTree<>(Optional.empty(), 0, mockContext);
        Entry<String, Geometry> entry = mock(Entry.class);
        Leaf<String, Geometry> leaf = mock(Leaf.class);
        when(mockFactory.createLeaf(anyList(), eq(mockContext))).thenReturn(leaf);

        // When
        RTree<String, Geometry> result = tree.add(entry);

        // Then
        assertEquals(1, result.size());
        verify(mockFactory).createLeaf(anyList(), eq(mockContext));
    }
}
