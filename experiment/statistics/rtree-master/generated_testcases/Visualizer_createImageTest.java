
package com.github.davidmoten.rtree;

import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Visualizer_createImageTest {

    private Visualizer visualizer;
    private RTree<?, Geometry> tree;
    private Node<?, Geometry> rootNode;

    @Before
    public void setUp() {
        tree = mock(RTree.class);
        rootNode = mock(Node.class);
        when(tree.root()).thenReturn(Optional.of(rootNode));
        visualizer = new Visualizer(tree, 100, 100, mock(Rectangle.class));
    }

    @Test
    public void testCreateImageWithRootNode() {
        // Given
        when(rootNode.geometry()).thenReturn(mock(Geometry.class));

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }

    @Test
    public void testCreateImageWithoutRootNode() {
        // Given
        when(tree.root()).thenReturn(Optional.empty());

        // When
        BufferedImage image = visualizer.createImage();

        // Then
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }
}
