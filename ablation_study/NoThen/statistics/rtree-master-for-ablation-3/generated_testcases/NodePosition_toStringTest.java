
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodePosition_toStringTest {

    @Test
    public void testToString() {
        // Given
        Node<String, Geometry> mockNode = mock(Node.class);
        when(mockNode.toString()).thenReturn("MockNode");
        NodePosition<String, Geometry> nodePosition = new NodePosition<>(mockNode, 42);

        // When
        String result = nodePosition.toString();

        // Then
        assertEquals("NodePosition [node=MockNode, position=42]", result);
    }
}
