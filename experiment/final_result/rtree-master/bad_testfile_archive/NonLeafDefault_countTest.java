
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class NonLeafDefault_countTest {

    private NonLeafDefault<Object, Geometry> nonLeaf;
    private List<Node<Object, Geometry>> children;

    @Before
    public void setUp() {
        Node<Object, Geometry> child1 = mock(Node.class);
        Node<Object, Geometry> child2 = mock(Node.class);
        children = Arrays.asList(child1, child2);
        nonLeaf = new NonLeafDefault<>(children, null);
    }

    @Test
    public void testCount() {
        assertEquals(2, nonLeaf.count());
    }
}
