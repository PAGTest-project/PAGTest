
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.github.davidmoten.rtree.internal.Node;
import com.google.flatbuffers.FlatBufferBuilder;

public class SerializerFlatBuffers_writeTest {

    private SerializerFlatBuffers<Object, Rectangle> serializer;
    private RTree<Object, Rectangle> tree;
    private ByteArrayOutputStream os;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
        tree = mock(RTree.class);
        os = new ByteArrayOutputStream();
    }

    @Test
    public void testWriteWithEmptyTree() throws IOException {
        when(tree.root()).thenReturn(Optional.empty());
        when(tree.size()).thenReturn(0);
        when(tree.context()).thenReturn(new Context<>(2, 4, null, null, null));

        serializer.write(tree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(0, t.size());
        assertEquals(0, t.root().childrenLength());
    }

    @Test
    public void testWriteWithNonEmptyTree() throws IOException {
        Rectangle mbb = Geometries.rectangle(0, 0, 10, 10);
        Node<Object, Rectangle> rootNode = mock(Node.class);
        when(rootNode.geometry()).thenReturn(mbb);
        when(tree.root()).thenReturn(Optional.of(rootNode));
        when(tree.size()).thenReturn(1);
        when(tree.context()).thenReturn(new Context<>(2, 4, null, null, null));

        serializer.write(tree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(1, t.size());
        assertEquals(mbb.x1(), t.root().mbb().x1());
        assertEquals(mbb.y1(), t.root().mbb().y1());
        assertEquals(mbb.x2(), t.root().mbb().x2());
        assertEquals(mbb.y2(), t.root().mbb().y2());
    }
}
