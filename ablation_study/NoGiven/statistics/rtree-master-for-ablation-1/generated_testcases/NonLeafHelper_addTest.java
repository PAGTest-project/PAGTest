
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.NonLeaf;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.ListPair;
import com.github.davidmoten.rtree.internal.Util;
import com.github.davidmoten.rtree.internal.Factory;
import com.github.davidmoten.rtree.internal.Selector;
import com.github.davidmoten.rtree.internal.Splitter;
import org.junit.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NonLeafHelper_addTest {

    @Test
    public void testAddWhenChildrenSizeLessThanOrEqualToMaxChildren() {
        // Given
        Entry<?, ? extends Geometry> entry = mock(Entry.class);
        NonLeaf<?, ? extends Geometry> node = mock(NonLeaf.class);
        Context<?, ? extends Geometry> context = mock(Context.class);
        Node<?, ? extends Geometry> child = mock(Node.class);
        List<Node<?, ? extends Geometry>> children = Collections.singletonList(child);

        when(node.context()).thenReturn(context);
        when(node.children()).thenReturn(children);
        when(context.selector()).thenReturn(mock(Selector.class));
        when(context.selector().select(any(), any())).thenReturn(child);
        when(child.add(entry)).thenReturn(children);
        when(context.maxChildren()).thenReturn(2);
        when(context.factory()).thenReturn(mock(Factory.class));
        when(context.factory().createNonLeaf(any(), any())).thenReturn(mock(NonLeaf.class));

        // When
        List<Node<?, ? extends Geometry>> result = NonLeafHelper.add(entry, node);

        // Then
        assertEquals(1, result.size());
    }

    @Test
    public void testAddWhenChildrenSizeGreaterThanMaxChildren() {
        // Given
        Entry<?, ? extends Geometry> entry = mock(Entry.class);
        NonLeaf<?, ? extends Geometry> node = mock(NonLeaf.class);
        Context<?, ? extends Geometry> context = mock(Context.class);
        Node<?, ? extends Geometry> child = mock(Node.class);
        List<Node<?, ? extends Geometry>> children = Collections.singletonList(child);

        when(node.context()).thenReturn(context);
        when(node.children()).thenReturn(children);
        when(context.selector()).thenReturn(mock(Selector.class));
        when(context.selector().select(any(), any())).thenReturn(child);
        when(child.add(entry)).thenReturn(children);
        when(context.maxChildren()).thenReturn(1);
        when(context.minChildren()).thenReturn(1);
        when(context.splitter()).thenReturn(mock(Splitter.class));
        when(context.splitter().split(any(), anyInt())).thenReturn(mock(ListPair.class));
        when(context.factory()).thenReturn(mock(Factory.class));
        when(context.factory().createNonLeaf(any(), any())).thenReturn(mock(NonLeaf.class));

        // When
        List<Node<?, ? extends Geometry>> result = NonLeafHelper.add(entry, node);

        // Then
        assertEquals(2, result.size());
    }
}
