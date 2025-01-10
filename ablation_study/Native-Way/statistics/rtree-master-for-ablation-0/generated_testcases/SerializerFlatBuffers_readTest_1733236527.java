
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.InternalStructure;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.SelectorRStar;
import com.github.davidmoten.rtree.SplitterRStar;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.fbs.generated.Tree_;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.internal.LeafFlatBuffers;
import com.github.davidmoten.rtree.internal.NonLeafFlatBuffers;

public class SerializerFlatBuffers_readTest {

    private SerializerFlatBuffers<Object, Geometry> serializer;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
    }

    @Test
    public void testReadWithEmptyTree() throws IOException {
        byte[] bytes = new byte[0];
        InputStream is = new ByteArrayInputStream(bytes);
        RTree<Object, Geometry> tree = serializer.read(is, 0, InternalStructure.SINGLE_ARRAY);
        assertTrue(tree.root().isEmpty());
    }

    @Test
    public void testReadWithNonEmptyTreeSingleArray() throws IOException {
        byte[] bytes = createTreeBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        RTree<Object, Geometry> tree = serializer.read(is, bytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(tree.root().isPresent());
        assertEquals(1, tree.size());
    }

    @Test
    public void testReadWithNonEmptyTreeDefault() throws IOException {
        byte[] bytes = createTreeBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        RTree<Object, Geometry> tree = serializer.read(is, bytes.length, InternalStructure.DEFAULT);
        assertTrue(tree.root().isPresent());
        assertEquals(1, tree.size());
    }

    private byte[] createTreeBytes() {
        // Create a simple tree structure for testing
        Tree_.startTree_(new com.google.flatbuffers.FlatBufferBuilder());
        Tree_.addContext(new com.google.flatbuffers.FlatBufferBuilder(), 0);
        Tree_.addSize(new com.google.flatbuffers.FlatBufferBuilder(), 1);
        Tree_.addRoot(new com.google.flatbuffers.FlatBufferBuilder(), 0);
        int treeOffset = Tree_.endTree_(new com.google.flatbuffers.FlatBufferBuilder());
        Tree_.finishTree_Buffer(new com.google.flatbuffers.FlatBufferBuilder(), treeOffset);
        ByteBuffer bb = new com.google.flatbuffers.FlatBufferBuilder().dataBuffer();
        return bb.array();
    }
}
