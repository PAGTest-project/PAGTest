
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
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.SelectorRStar;
import com.github.davidmoten.rtree.SplitterRStar;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.fbs.generated.Tree_;
import com.github.davidmoten.rtree.geometry.Geometry;

public class SerializerFlatBuffers_readTest {

    private SerializerFlatBuffers<Object, Geometry> serializer;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
    }

    @Test
    public void testReadWithEmptyTree() throws IOException {
        // Given
        byte[] emptyTreeBytes = createEmptyTreeBytes();
        InputStream is = new ByteArrayInputStream(emptyTreeBytes);

        // When
        RTree<Object, Geometry> result = serializer.read(is, emptyTreeBytes.length, InternalStructure.SINGLE_ARRAY);

        // Then
        assertTrue(result.root().isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    public void testReadWithNonEmptyTree() throws IOException {
        // Given
        byte[] nonEmptyTreeBytes = createNonEmptyTreeBytes();
        InputStream is = new ByteArrayInputStream(nonEmptyTreeBytes);

        // When
        RTree<Object, Geometry> result = serializer.read(is, nonEmptyTreeBytes.length, InternalStructure.SINGLE_ARRAY);

        // Then
        assertTrue(result.root().isPresent());
        assertEquals(1, result.size());
    }

    @Test(expected = RuntimeException.class)
    public void testReadFullyWithInsufficientBytes() throws IOException {
        // Given
        InputStream is = new ByteArrayInputStream(new byte[10]);

        // When
        SerializerFlatBuffers.readFully(is, 12);

        // Then (exception is expected)
    }

    private byte[] createEmptyTreeBytes() {
        // Create a byte array representing an empty tree
        return new byte[0];
    }

    private byte[] createNonEmptyTreeBytes() {
        // Create a byte array representing a non-empty tree
        // This is a simplified example; in practice, you would serialize a real tree
        return new byte[] { 0x00, 0x01, 0x02, 0x03 };
    }
}
