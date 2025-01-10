
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class NonLeafDefault_addTest {

    @Test
    public void testAdd() {
        // Given
        Context<String, Rectangle> context = mock(Context.class);
        Node<String, Rectangle> childNode = mock(Node.class);
        List<Node<String, Rectangle>> children = Collections.singletonList(childNode);
        NonLeafDefault<String, Rectangle> nonLeaf = new NonLeafDefault<>(children, context);
        Entry<String, Rectangle> entry = mock(Entry.class);

        // When
        List<Node<String, Rectangle>> result = nonLeaf.add(entry);

        // Then
        assertNotNull(result);
    }
}
