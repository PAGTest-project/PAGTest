
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.davidmoten.rtree.geometry.Geometry;

public class RTree_addTest {

    @Mock
    private Node<String, Geometry> mockNode;

    @Mock
    private Context<String, Geometry> mockContext;

    @Mock
    private Factory<String, Geometry> mockFactory;

    @Mock
    private Entry<String, Geometry> mockEntry;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockContext.factory()).thenReturn(mockFactory);
    }

    @Test
    public void testAddWhenRootIsPresent() {
        RTree<String, Geometry> tree = new RTree<>(Optional.of(mockNode), 1, mockContext);
        List<Node<String, Geometry>> nodes = mock(List.class);
        Node<String, Geometry> newNode = mock(Node.class);

        when(mockNode.add(any(Entry.class))).thenReturn(nodes);
        when(nodes.size()).thenReturn(1);
        when(nodes.get(0)).thenReturn(newNode);

        RTree<String, Geometry> result = tree.add(mockEntry);

        assertEquals(2, result.size());
        assertEquals(newNode, result.root().get());
    }

    @Test
    public void testAddWhenRootIsNotPresent() {
        RTree<String, Geometry> tree = new RTree<>(Optional.empty(), 0, mockContext);
        Leaf<String, Geometry> leaf = mock(Leaf.class);

        when(mockFactory.createLeaf(anyList(), eq(mockContext))).thenReturn(leaf);

        RTree<String, Geometry> result = tree.add(mockEntry);

        assertEquals(1, result.size());
        assertEquals(leaf, result.root().get());
    }
}
