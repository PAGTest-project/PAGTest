
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RectangleUtil_rectangleIntersectsLineTest {

    @Test
    public void testRectangleIntersectsLine_LineIntersectsRectangle() {
        // Given
        double rectX = 0, rectY = 0, rectWidth = 10, rectHeight = 10;
        double x1 = -5, y1 = 5, x2 = 15, y2 = 5;

        // When
        boolean result = RectangleUtil.rectangleIntersectsLine(rectX, rectY, rectWidth, rectHeight, x1, y1, x2, y2);

        // Then
        assertTrue(result);
    }

    @Test
    public void testRectangleIntersectsLine_LineDoesNotIntersectRectangle() {
        // Given
        double rectX = 0, rectY = 0, rectWidth = 10, rectHeight = 10;
        double x1 = -5, y1 = -5, x2 = -5, y2 = 15;

        // When
        boolean result = RectangleUtil.rectangleIntersectsLine(rectX, rectY, rectWidth, rectHeight, x1, y1, x2, y2);

        // Then
        assertFalse(result);
    }
}
