
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.internal.NodeAndEntries;

public class RTree_addTest {

    private RTree<Object, Geometry> rTree;
    private Context<Object, Geometry> context;
    private Node<Object, Geometry> mockNode;
    private Entry<Object, Geometry> mockEntry;

    @Before
    public void setUp() {
        context = mock(Context.class);
        mockNode = mock(Node.class);
        mockEntry = mock(Entry.class);
        rTree = new RTree<>(Optional.of(mockNode), 1, context);
    }

    @Test
    public void testAddWhenRootIsPresentAndNodesSizeIsOne() {
        List<Node<Object, Geometry>> nodes = mock(List.class);
        when(mockNode.add(mockEntry)).thenReturn(nodes);
        when(nodes.size()).thenReturn(1);
        when(nodes.get(0)).thenReturn(mockNode);

        RTree<Object, Geometry> result = rTree.add(mockEntry);

        assertEquals(2, result.size());
        verify(mockNode).add(mockEntry);
    }

    @Test
    public void testAddWhenRootIsPresentAndNodesSizeIsGreaterThanOne() {
        List<Node<Object, Geometry>> nodes = mock(List.class);
        Node<Object, Geometry> newNode = mock(Node.class);
        when(mockNode.add(mockEntry)).thenReturn(nodes);
        when(nodes.size()).thenReturn(2);
        when(context.factory()).thenReturn(mock(Factory.class));
        when(context.factory().createNonLeaf(nodes, context)).thenReturn(newNode);

        RTree<Object, Geometry> result = rTree.add(mockEntry);

        assertEquals(2, result.size());
        verify(mockNode).add(mockEntry);
        verify(context.factory()).createNonLeaf(nodes, context);
    }

    @Test
    public void testAddWhenRootIsNotPresent() {
        rTree = new RTree<>(Optional.empty(), 0, context);
        Leaf<Object, Geometry> leaf = mock(Leaf.class);
        when(context.factory()).thenReturn(mock(Factory.class));
        when(context.factory().createLeaf(anyList(), eq(context))).thenReturn(leaf);

        RTree<Object, Geometry> result = rTree.add(mockEntry);

        assertEquals(1, result.size());
        verify(context.factory()).createLeaf(anyList(), eq(context));
    }
}
