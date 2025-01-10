
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class NonLeafDefault_childrenTest {

    private NonLeafDefault<String, Rectangle> nonLeaf;
    private Node<String, Rectangle> child1;
    private Node<String, Rectangle> child2;

    @Before
    public void setUp() {
        child1 = new LeafDefault<>("child1", Rectangle.create(0, 0, 1, 1));
        child2 = new LeafDefault<>("child2", Rectangle.create(1, 1, 2, 2));
        List<Node<String, Rectangle>> children = Arrays.asList(child1, child2);
        nonLeaf = new NonLeafDefault<>(children, new Context<>());
    }

    @Test
    public void testChildren() {
        List<Node<String, Rectangle>> result = nonLeaf.children();
        assertEquals(2, result.size());
        assertTrue(result.contains(child1));
        assertTrue(result.contains(child2));
    }
}
