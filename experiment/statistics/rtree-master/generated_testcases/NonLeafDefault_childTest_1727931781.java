
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class NonLeafDefault_childTest {

    private NonLeafDefault<String, Rectangle> nonLeaf;
    private Node<String, Rectangle> child1;
    private Node<String, Rectangle> child2;

    @Before
    public void setUp() {
        child1 = new LeafDefault<>("entry1", Rectangle.create(0, 0, 1, 1));
        child2 = new LeafDefault<>("entry2", Rectangle.create(2, 2, 3, 3));
        List<Node<String, Rectangle>> children = Arrays.asList(child1, child2);
        Context<String, Rectangle> context = new Context<>(null, null, null, null);
        nonLeaf = new NonLeafDefault<>(children, context);
    }

    @Test
    public void testChild() {
        Node<String, Rectangle> retrievedChild = nonLeaf.child(0);
        assertNotNull(retrievedChild);
        assertEquals(child1, retrievedChild);
    }
}
