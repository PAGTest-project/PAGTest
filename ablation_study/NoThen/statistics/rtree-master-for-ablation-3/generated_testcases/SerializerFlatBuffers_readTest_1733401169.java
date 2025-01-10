
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
        ByteBuffer bb = ByteBuffer.allocate(1024);
        Tree_.startTree_(bb);
        Tree_.addContext(bb, 0);
        Tree_.addSize(bb, 0);
        int t = Tree_.endTree_(bb);
        Tree_.finishTree_Buffer(bb, t);
        return bb.array();
    }

    private byte[] createNonEmptyTreeBytes() {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        Tree_.startTree_(bb);
        Tree_.addContext(bb, 0);
        Tree_.addSize(bb, 1);
        Tree_.addRoot(bb, 0);
        int t = Tree_.endTree_(bb);
        Tree_.finishTree_Buffer(bb, t);
        return bb.array();
    }

    @Test(expected = RuntimeException.class)
    public void testInputStreamNotAsLongAsExpected() throws IOException {
        SerializerFlatBuffers.readFully(new ByteArrayInputStream(new byte[10]), 12);
    }
}
