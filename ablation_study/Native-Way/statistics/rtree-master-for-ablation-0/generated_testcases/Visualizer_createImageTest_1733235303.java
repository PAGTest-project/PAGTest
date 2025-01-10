
package com.github.davidmoten.rtree;

import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import static org.junit.Assert.*;

public class Visualizer_createImageTest {
    private Visualizer visualizer;

    @Before
    public void setUp() {
        RTree<Object, Geometry> tree = RTree.create();
        visualizer = new Visualizer(tree, 100, 100, com.github.davidmoten.rtree.geometry.Rectangle.create(0, 0, 100, 100));
    }

    @Test
    public void testCreateImageWithRootPresent() {
        BufferedImage image = visualizer.createImage();
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }

    @Test
    public void testCreateImageWithRootNotPresent() {
        RTree<Object, Geometry> emptyTree = RTree.create();
        Visualizer emptyVisualizer = new Visualizer(emptyTree, 100, 100, com.github.davidmoten.rtree.geometry.Rectangle.create(0, 0, 100, 100));
        BufferedImage image = emptyVisualizer.createImage();
        assertNotNull(image);
        assertEquals(100, image.getWidth());
        assertEquals(100, image.getHeight());
    }
}
