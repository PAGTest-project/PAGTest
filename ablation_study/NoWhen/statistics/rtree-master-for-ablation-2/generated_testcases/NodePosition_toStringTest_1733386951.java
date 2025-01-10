
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NodePosition_toStringTest {

    @Test
    public void testToString() {
        Node<String, Point> node = new NonLeaf<>(null, null, null, null);
        NodePosition<String, Point> nodePosition = new NodePosition<>(node, 5);

        String expected = "NodePosition [node=" + node + ", position=5]";
        assertEquals(expected, nodePosition.toString());
    }
}
