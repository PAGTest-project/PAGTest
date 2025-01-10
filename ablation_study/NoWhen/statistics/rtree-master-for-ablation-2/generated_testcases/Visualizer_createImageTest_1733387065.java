
package com.github.davidmoten.rtree;

import org.junit.Test;
import java.awt.image.BufferedImage;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Visualizer_createImageTest {

    @Test
    public void testCreateImageWithRootPresent() {
        // Given
        RTree<?, Geometry> tree = mock(RTree.class);
        Node<?, Geometry> rootNode = mock(Node.class);
        when(tree.root()).thenReturn(Optional.of(rootNode));

        Visualizer visualizer = new Visualizer(tree, 100, 100, mock(Rectangle.class));

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }

    @Test
    public void testCreateImageWithRootNotPresent() {
        // Given
        RTree<?, Geometry> tree = mock(RTree.class);
        when(tree.root()).thenReturn(Optional.empty());

        Visualizer visualizer = new Visualizer(tree, 100, 100, mock(Rectangle.class));

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }
}
