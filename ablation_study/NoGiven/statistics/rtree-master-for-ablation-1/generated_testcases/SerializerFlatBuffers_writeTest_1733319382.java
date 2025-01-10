
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

import rx.functions.Func1;

import com.github.davidmoten.rtree.fbs.generated.Tree_;

public class SerializerFlatBuffers_writeTest {

    private SerializerFlatBuffers<String, Point> serializer;
    private RTree<String, Point> tree;

    @Before
    public void setUp() {
        Func1<String, byte[]> serializerFunc = s -> s.getBytes();
        Func1<byte[], String> deserializerFunc = bytes -> new String(bytes);
        serializer = new SerializerFlatBuffers<>(serializerFunc, deserializerFunc);

        tree = RTree.create();
        tree = tree.add("Entry1", Geometries.point(1, 1));
        tree = tree.add("Entry2", Geometries.point(2, 2));
    }

    @Test
    public void testWriteWithEmptyTree() throws IOException {
        RTree<String, Point> emptyTree = RTree.create();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(emptyTree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(0, t.size());
        assertTrue(t.root() == null);
    }

    @Test
    public void testWriteWithNonEmptyTree() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(2, t.size());
        assertTrue(t.root() != null);
    }

    @Test
    public void testWriteWithSingleEntryTree() throws IOException {
        RTree<String, Point> singleEntryTree = RTree.create();
        singleEntryTree = singleEntryTree.add("Entry1", Geometries.point(1, 1));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(singleEntryTree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(1, t.size());
        assertTrue(t.root() != null);
    }

    @Test
    public void testWriteWithDoublePrecision() throws IOException {
        RTree<String, Point> doublePrecisionTree = RTree.create();
        doublePrecisionTree = doublePrecisionTree.add("Entry1", Geometries.point(1.1, 1.1));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(doublePrecisionTree, os);

        ByteBuffer bb = ByteBuffer.wrap(os.toByteArray());
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(1, t.size());
        assertTrue(t.root() != null);
    }
}
