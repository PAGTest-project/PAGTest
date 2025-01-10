
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.github.davidmoten.rtree.internal.LeafDefault;
import com.github.davidmoten.rtree.internal.NonLeafDefault;
import com.google.flatbuffers.FlatBufferBuilder;
import com.github.davidmoten.rtree.fbs.generated.Tree_;
import com.github.davidmoten.rtree.fbs.generated.Node_;

public class SerializerFlatBuffers_writeTest {

    private SerializerFlatBuffers<Object, Point> serializer;
    private RTree<Object, Point> tree;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
        tree = RTree.create();
    }

    @Test
    public void testWriteWithEmptyTree() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(0, t.size());
        assertTrue(t.root() == null);
    }

    @Test
    public void testWriteWithSingleLeafNode() throws IOException {
        Entry<Object, Point> entry = new Entry<>(new Object(), Geometries.point(1, 2));
        tree = tree.add(entry);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(1, t.size());
        Node_ node = t.root();
        assertTrue(node != null);
        assertEquals(0, node.childrenLength());
    }

    @Test
    public void testWriteWithNonLeafNode() throws IOException {
        Entry<Object, Point> entry1 = new Entry<>(new Object(), Geometries.point(1, 2));
        Entry<Object, Point> entry2 = new Entry<>(new Object(), Geometries.point(3, 4));
        tree = tree.add(entry1).add(entry2);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(2, t.size());
        Node_ node = t.root();
        assertTrue(node != null);
        assertEquals(2, node.childrenLength());
    }

    @Test
    public void testWriteWithCustomContext() throws IOException {
        Context<Object, Point> context = new Context<>(3, 7, null, null, null);
        tree = RTree.create(context);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(3, t.context().minChildren());
        assertEquals(7, t.context().maxChildren());
    }
}
