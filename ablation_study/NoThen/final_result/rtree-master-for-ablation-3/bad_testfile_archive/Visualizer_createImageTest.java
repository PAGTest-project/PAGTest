
package com.github.davidmoten.rtree;

import org.junit.Test;
import java.awt.image.BufferedImage;
import java.util.Optional;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class Visualizer_createImageTest {

    @Test
    public void testCreateImageWithRootPresent() {
        // Given
        RTree<?, Geometry> mockTree = mock(RTree.class);
        Node<?, Geometry> mockNode = mock(Node.class);
        when(mockTree.root()).thenReturn(Optional.of(mockNode));

        Visualizer visualizer = new Visualizer(mockTree, 100, 100, mock(Rectangle.class));

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
    }

    @Test
    public void testCreateImageWithRootNotPresent() {
        // Given
        RTree<?, Geometry> mockTree = mock(RTree.class);
        when(mockTree.root()).thenReturn(Optional.empty());

        Visualizer visualizer = new Visualizer(mockTree, 100, 100, mock(Rectangle.class));

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
    }
}
