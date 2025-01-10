
package com.github.davidmoten.rtree.geometry.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.github.davidmoten.rtree.geometry.Circle;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Line;
import com.github.davidmoten.rtree.geometry.internal.Vector;

public class GeometryUtil_lineIntersectsTest {

    @Test
    public void testLineIntersects_LineSegmentIntersectsCircle() {
        Circle circle = new Circle() {
            @Override
            public double x() {
                return 0;
            }

            @Override
            public double y() {
                return 0;
            }

            @Override
            public double radius() {
                return 1;
            }

            @Override
            public boolean intersects(Circle other) {
                return false;
            }

            @Override
            public boolean intersects(Point point) {
                return false;
            }

            @Override
            public boolean intersects(Line line) {
                return false;
            }

            @Override
            public boolean isDoublePrecision() {
                return true;
            }
        };

        assertTrue(GeometryUtil.lineIntersects(0, 1, 0, -1, circle));
    }

    @Test
    public void testLineIntersects_LineSegmentDoesNotIntersectCircle() {
        Circle circle = new Circle() {
            @Override
            public double x() {
                return 0;
            }

            @Override
            public double y() {
                return 0;
            }

            @Override
            public double radius() {
                return 1;
            }

            @Override
            public boolean intersects(Circle other) {
                return false;
            }

            @Override
            public boolean intersects(Point point) {
                return false;
            }

            @Override
            public boolean intersects(Line line) {
                return false;
            }

            @Override
            public boolean isDoublePrecision() {
                return true;
            }
        };

        assertFalse(GeometryUtil.lineIntersects(0, 2, 0, -2, circle));
    }

    @Test
    public void testLineIntersects_PointIntersectsCircle() {
        Circle circle = new Circle() {
            @Override
            public double x() {
                return 0;
            }

            @Override
            public double y() {
                return 0;
            }

            @Override
            public double radius() {
                return 1;
            }

            @Override
            public boolean intersects(Circle other) {
                return false;
            }

            @Override
            public boolean intersects(Point point) {
                return false;
            }

            @Override
            public boolean intersects(Line line) {
                return false;
            }

            @Override
            public boolean isDoublePrecision() {
                return true;
            }
        };

        assertTrue(GeometryUtil.lineIntersects(0, 0, 0, 0, circle));
    }

    @Test
    public void testLineIntersects_PointDoesNotIntersectCircle() {
        Circle circle = new Circle() {
            @Override
            public double x() {
                return 0;
            }

            @Override
            public double y() {
                return 0;
            }

            @Override
            public double radius() {
                return 1;
            }

            @Override
            public boolean intersects(Circle other) {
                return false;
            }

            @Override
            public boolean intersects(Point point) {
                return false;
            }

            @Override
            public boolean intersects(Line line) {
                return false;
            }

            @Override
            public boolean isDoublePrecision() {
                return true;
            }
        };

        assertFalse(GeometryUtil.lineIntersects(2, 2, 2, 2, circle));
    }
}
