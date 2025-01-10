
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
    private List<Node<String, Rectangle>> children;
    private Context<String, Rectangle> context;

    @Before
    public void setUp() {
        children = Arrays.asList(
            new LeafDefault<>("child1", Rectangle.create(0, 0, 1, 1)),
            new LeafDefault<>("child2", Rectangle.create(1, 1, 2, 2))
        );
        context = new Context<>(1, 2, null, null, null);
        nonLeaf = new NonLeafDefault<>(children, context);
    }

    @Test
    public void testChildren() {
        List<Node<String, Rectangle>> result = nonLeaf.children();
        assertEquals(children, result);
        assertTrue(result.containsAll(children));
    }
}
