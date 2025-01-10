
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.fbs.generated.Bounds_;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.geometry.Geometry;

import rx.functions.Func1;

public class NonLeafFlatBuffers_toStringTest {

    @Test
    public void testToStringNonLeaf() {
        // Given
        Node_ node = mock(Node_.class);
        when(node.childrenLength()).thenReturn(1); // NonLeaf
        Bounds_ bounds = mock(Bounds_.class);
        when(node.mbb()).thenReturn(bounds);
        when(FlatBuffersHelper.createBox(bounds).toString()).thenReturn("Box");

        Context<Object, Geometry> context = mock(Context.class);
        Func1<byte[], ? extends Object> deserializer = mock(Func1.class);

        NonLeafFlatBuffers<Object, Geometry> nonLeaf = new NonLeafFlatBuffers<>(node, context, deserializer);

        // When
        String result = nonLeaf.toString();

        // Then
        assertEquals("Node [NonLeaf,Box]", result);
    }

    @Test
    public void testToStringLeaf() {
        // Given
        Node_ node = mock(Node_.class);
        when(node.childrenLength()).thenReturn(0); // Leaf
        Bounds_ bounds = mock(Bounds_.class);
        when(node.mbb()).thenReturn(bounds);
        when(FlatBuffersHelper.createBox(bounds).toString()).thenReturn("Box");

        Context<Object, Geometry> context = mock(Context.class);
        Func1<byte[], ? extends Object> deserializer = mock(Func1.class);

        NonLeafFlatBuffers<Object, Geometry> nonLeaf = new NonLeafFlatBuffers<>(node, context, deserializer);

        // When
        String result = nonLeaf.toString();

        // Then
        assertEquals("Node [Leaf,Box]", result);
    }
}
