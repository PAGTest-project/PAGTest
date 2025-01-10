
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NodePosition_toStringTest {

    @Test
    public void testToString() {
        Node<String, Point> node = new NonLeaf<String, Point>(null, null, null, null) {
            @Override
            public Rectangle mbr() {
                return null;
            }

            @Override
            public Rectangle mbr(Rectangle rectangle) {
                return null;
            }

            @Override
            public int count() {
                return 0;
            }

            @Override
            public Iterable<Node<String, Point>> children() {
                return null;
            }

            @Override
            public boolean isLeaf() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Context<String, Point> context() {
                return null;
            }

            @Override
            public Node<String, Point> child(int i) {
                return null;
            }

            @Override
            public String value() {
                return null;
            }

            @Override
            public Geometry geometry() {
                return null;
            }
        };
        NodePosition<String, Point> nodePosition = new NodePosition<>(node, 5);

        String expected = "NodePosition [node=" + node + ", position=5]";
        assertEquals(expected, nodePosition.toString());
    }
}
