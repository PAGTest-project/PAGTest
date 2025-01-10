
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
import com.github.davidmoten.rtree.internal.FactoryDefault;

public class SerializerFlatBuffers_readTest {

    private SerializerFlatBuffers<Object, Geometry> serializer;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
    }

    @Test
    public void testReadWithEmptyTree() throws IOException {
        byte[] emptyTreeBytes = createEmptyTreeBytes();
        InputStream is = new ByteArrayInputStream(emptyTreeBytes);
        RTree<Object, Geometry> tree = serializer.read(is, emptyTreeBytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(tree.root().isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    public void testReadWithNonEmptyTree() throws IOException {
        byte[] nonEmptyTreeBytes = createNonEmptyTreeBytes();
        InputStream is = new ByteArrayInputStream(nonEmptyTreeBytes);
        RTree<Object, Geometry> tree = serializer.read(is, nonEmptyTreeBytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(tree.root().isPresent());
        assertEquals(1, tree.size());
    }

    private byte[] createEmptyTreeBytes() {
        // Create an empty tree in FlatBuffers format
        com.google.flatbuffers.FlatBufferBuilder builder = new com.google.flatbuffers.FlatBufferBuilder();
        int contextOffset = com.github.davidmoten.rtree.fbs.generated.Context_.createContext_(builder, 0, 0);
        Tree_.startTree_(builder);
        Tree_.addContext(builder, contextOffset);
        Tree_.addSize(builder, 0);
        int treeOffset = Tree_.endTree_(builder);
        Tree_.finishTree_Buffer(builder, treeOffset);
        return builder.sizedByteArray();
    }

    private byte[] createNonEmptyTreeBytes() {
        // Create a non-empty tree in FlatBuffers format
        com.google.flatbuffers.FlatBufferBuilder builder = new com.google.flatbuffers.FlatBufferBuilder();
        int contextOffset = com.github.davidmoten.rtree.fbs.generated.Context_.createContext_(builder, 2, 4);
        int nodeOffset = com.github.davidmoten.rtree.fbs.generated.Node_.createNode_(builder, 0, 0, 0);
        Tree_.startTree_(builder);
        Tree_.addContext(builder, contextOffset);
        Tree_.addRoot(builder, nodeOffset);
        Tree_.addSize(builder, 1);
        int treeOffset = Tree_.endTree_(builder);
        Tree_.finishTree_Buffer(builder, treeOffset);
        return builder.sizedByteArray();
    }
}
