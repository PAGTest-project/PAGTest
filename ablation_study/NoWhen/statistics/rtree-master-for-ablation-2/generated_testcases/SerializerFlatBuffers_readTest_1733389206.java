
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.InternalStructure;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.SelectorRStar;
import com.github.davidmoten.rtree.SplitterRStar;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.fbs.generated.Tree_;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.google.flatbuffers.FlatBufferBuilder;

public class SerializerFlatBuffers_readTest {

    private SerializerFlatBuffers<Object, Geometry> serializer;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
    }

    @Test
    public void testReadWithEmptyTree() throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int treeOffset = Tree_.createTree_(builder, 0, 0, 0);
        Tree_.finishTree_Buffer(builder, treeOffset);
        byte[] bytes = builder.sizedByteArray();

        RTree<Object, Geometry> result = serializer.read(new ByteArrayInputStream(bytes), bytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(result.root().isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    public void testReadWithNonEmptyTree() throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int contextOffset = Context_.createContext_(builder, 2, 4, 0, 0);
        int nodeOffset = Node_.createNode_(builder, 0, 0, 0);
        int treeOffset = Tree_.createTree_(builder, contextOffset, nodeOffset, 1);
        Tree_.finishTree_Buffer(builder, treeOffset);
        byte[] bytes = builder.sizedByteArray();

        RTree<Object, Geometry> result = serializer.read(new ByteArrayInputStream(bytes), bytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(result.root().isPresent());
        assertEquals(1, result.size());
    }

    @Test
    public void testReadWithNonLeafNode() throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int contextOffset = Context_.createContext_(builder, 2, 4, 0, 0);
        int childNodeOffset = Node_.createNode_(builder, 0, 0, 0);
        int[] childrenOffsets = new int[] { childNodeOffset };
        int childrenVectorOffset = Node_.createChildrenVector(builder, childrenOffsets);
        int nodeOffset = Node_.createNode_(builder, 0, childrenVectorOffset, 0);
        int treeOffset = Tree_.createTree_(builder, contextOffset, nodeOffset, 1);
        Tree_.finishTree_Buffer(builder, treeOffset);
        byte[] bytes = builder.sizedByteArray();

        RTree<Object, Geometry> result = serializer.read(new ByteArrayInputStream(bytes), bytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(result.root().isPresent());
        assertEquals(1, result.size());
        Node<Object, Geometry> root = result.root().get();
        assertTrue(root instanceof NonLeafFlatBuffers);
    }

    @Test
    public void testReadWithLeafNode() throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int contextOffset = Context_.createContext_(builder, 2, 4, 0, 0);
        int nodeOffset = Node_.createNode_(builder, 0, 0, 0);
        int treeOffset = Tree_.createTree_(builder, contextOffset, nodeOffset, 1);
        Tree_.finishTree_Buffer(builder, treeOffset);
        byte[] bytes = builder.sizedByteArray();

        RTree<Object, Geometry> result = serializer.read(new ByteArrayInputStream(bytes), bytes.length, InternalStructure.SINGLE_ARRAY);
        assertTrue(result.root().isPresent());
        assertEquals(1, result.size());
        Node<Object, Geometry> root = result.root().get();
        assertTrue(root instanceof LeafFlatBuffers);
    }
}
