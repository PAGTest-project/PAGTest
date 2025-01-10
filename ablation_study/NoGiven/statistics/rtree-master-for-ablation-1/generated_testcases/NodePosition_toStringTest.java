
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodePosition_toStringTest {

    @Test
    public void testToString() {
        Node<String, Geometry> mockNode = mock(Node.class);
        when(mockNode.toString()).thenReturn("MockNode");

        NodePosition<String, Geometry> nodePosition = new NodePosition<>(mockNode, 42);

        String expected = "NodePosition [node=MockNode, position=42]";
        assertEquals(expected, nodePosition.toString());
    }
}
