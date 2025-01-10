
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.google.flatbuffers.FlatBufferBuilder;
import com.github.davidmoten.rtree.fbs.generated.Tree_;
import com.github.davidmoten.rtree.fbs.generated.Bounds_;
import com.github.davidmoten.rtree.fbs.generated.BoxDouble_;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.fbs.generated.BoundsType_;

public class SerializerFlatBuffers_writeTest {

    private SerializerFlatBuffers<Object, Point> serializer;

    @Before
    public void setUp() {
        serializer = new SerializerFlatBuffers<>(null, null);
    }

    @Test
    public void testWriteWithEmptyTree() throws IOException {
        RTree<Object, Point> tree = RTree.create();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(0, t.size());
        assertTrue(t.root() == 0);
    }

    @Test
    public void testWriteWithNonEmptyTree() throws IOException {
        RTree<Object, Point> tree = RTree.create(
                Collections.singletonList(
                        new Entry<Object, Point>() {
                            @Override
                            public Object value() {
                                return "test";
                            }

                            @Override
                            public Point geometry() {
                                return Geometries.point(1, 2);
                            }
                        }
                )
        );
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.write(tree, os);
        byte[] bytes = os.toByteArray();
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        Tree_ t = Tree_.getRootAsTree_(bb);
        assertEquals(1, t.size());
        assertTrue(t.root() != 0);
    }

    @Test
    public void testToBounds() {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        Rectangle rect = Geometries.rectangle(0, 0, 1, 1);
        int boundsOffset = serializer.toBounds(builder, rect);
        Bounds_ bounds = Bounds_.getRootAsBounds_(builder.dataBuffer());
        assertEquals(BoundsType_.BoundsDouble, bounds.type());
        BoxDouble_ box = bounds.boxDouble();
        assertEquals(0.0, box.x1(), 0.001);
        assertEquals(0.0, box.y1(), 0.001);
        assertEquals(1.0, box.x2(), 0.001);
        assertEquals(1.0, box.y2(), 0.001);
    }

    @Test
    public void testAddNode() {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        RTree<Object, Point> tree = RTree.create(
                Collections.singletonList(
                        new Entry<Object, Point>() {
                            @Override
                            public Object value() {
                                return "test";
                            }

                            @Override
                            public Point geometry() {
                                return Geometries.point(1, 2);
                            }
                        }
                )
        );
        int nodeOffset = serializer.addNode(tree.root().get(), builder, null);
        Node_ node = Node_.getRootAsNode_(builder.dataBuffer());
        assertEquals(1, node.childrenLength());
    }
}
