
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class RTree_visualizeTest {

    @Test
    public void testVisualize() {
        // Given
        RTree<Object, Geometry> tree = RTree.create();
        int width = 800;
        int height = 600;
        Rectangle view = Rectangle.create(0, 0, 100, 100);

        // When
        Visualizer visualizer = tree.visualize(width, height, view);

        // Then
        assertNotNull(visualizer);
    }
}
