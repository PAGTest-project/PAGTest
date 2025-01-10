
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NonLeafDefault_childrenTest {

    private NonLeafDefault<String, Rectangle> nonLeafDefault;
    private List<Node<String, Rectangle>> children;
    private Context<String, Rectangle> context;

    @Before
    public void setUp() {
        Node<String, Rectangle> child1 = mock(Node.class);
        Node<String, Rectangle> child2 = mock(Node.class);
        children = Arrays.asList(child1, child2);
        context = mock(Context.class);
        nonLeafDefault = new NonLeafDefault<>(children, context);
    }

    @Test
    public void testChildren() {
        List<Node<String, Rectangle>> result = nonLeafDefault.children();
        assertEquals(children, result);
    }
}
