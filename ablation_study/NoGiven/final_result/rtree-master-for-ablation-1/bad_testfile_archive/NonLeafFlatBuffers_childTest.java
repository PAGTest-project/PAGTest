
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.geometry.Geometry;

import rx.functions.Func1;

public class NonLeafFlatBuffers_childTest {

    @Test
    public void testChildWithNonLeaf() {
        // Given
        Node_ mockNode = Mockito.mock(Node_.class);
        Node_ mockChild = Mockito.mock(Node_.class);
        Context<String, Geometry> mockContext = Mockito.mock(Context.class);
        Func1<byte[], String> mockDeserializer = Mockito.mock(Func1.class);

        when(mockNode.children(0)).thenReturn(mockChild);
        when(mockChild.childrenLength()).thenReturn(1); // Non-leaf child

        NonLeafFlatBuffers<String, Geometry> nonLeaf = new NonLeafFlatBuffers<>(mockNode, mockContext, mockDeserializer);

        // When
        Node<String, Geometry> result = nonLeaf.child(0);

        // Then
        assertTrue(result instanceof NonLeafFlatBuffers);
    }

    @Test
    public void testChildWithLeaf() {
        // Given
        Node_ mockNode = Mockito.mock(Node_.class);
        Node_ mockChild = Mockito.mock(Node_.class);
        Context<String, Geometry> mockContext = Mockito.mock(Context.class);
        Func1<byte[], String> mockDeserializer = Mockito.mock(Func1.class);

        when(mockNode.children(0)).thenReturn(mockChild);
        when(mockChild.childrenLength()).thenReturn(0); // Leaf child

        NonLeafFlatBuffers<String, Geometry> nonLeaf = new NonLeafFlatBuffers<>(mockNode, mockContext, mockDeserializer);

        // When
        Node<String, Geometry> result = nonLeaf.child(0);

        // Then
        assertTrue(result instanceof LeafFlatBuffers);
    }
}
