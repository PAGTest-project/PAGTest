
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.github.davidmoten.rtree.Context;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NonLeafDefault_deleteTest {

    @Test
    public void testDelete() {
        // Given
        Context<String, Rectangle> context = mock(Context.class);
        Node<String, Rectangle> childNode = mock(Node.class);
        List<Node<String, Rectangle>> children = Collections.singletonList(childNode);
        NonLeafDefault<String, Rectangle> nonLeaf = new NonLeafDefault<>(children, context);

        Entry<String, Rectangle> entry = mock(Entry.class);
        NodeAndEntries<String, Rectangle> expectedResult = new NodeAndEntries<>(Optional.empty(), Collections.emptyList(), 1);

        // Mock NonLeafHelper.delete to return the expected result
        NonLeafHelper nonLeafHelper = mock(NonLeafHelper.class);
        when(nonLeafHelper.delete(eq(entry), eq(true), eq(nonLeaf))).thenReturn(expectedResult);

        // When
        NodeAndEntries<String, Rectangle> result = nonLeaf.delete(entry, true);

        // Then
        assertEquals(expectedResult, result);
    }
}
