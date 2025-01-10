
package com.github.davidmoten.rtree.fbs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.github.davidmoten.rtree.fbs.generated.Bounds_;
import com.github.davidmoten.rtree.fbs.generated.BoundsType_;
import com.github.davidmoten.rtree.fbs.generated.BoxDouble_;
import com.github.davidmoten.rtree.fbs.generated.BoxFloat_;
import com.github.davidmoten.rtree.fbs.generated.Node_;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;

public class LeafFlatBuffers_geometryTest {

    @Test
    public void testGeometryWithBoundsDouble() {
        // Given
        Node_ node = mock(Node_.class);
        Bounds_ bounds = mock(Bounds_.class);
        BoxDouble_ boxDouble = mock(BoxDouble_.class);

        when(node.mbb()).thenReturn(bounds);
        when(bounds.type()).thenReturn(BoundsType_.BoundsDouble);
        when(bounds.boxDouble()).thenReturn(boxDouble);
        when(boxDouble.minX()).thenReturn(0.0);
        when(boxDouble.minY()).thenReturn(0.0);
        when(boxDouble.maxX()).thenReturn(10.0);
        when(boxDouble.maxY()).thenReturn(10.0);

        LeafFlatBuffers<Object, Geometry> leaf = new LeafFlatBuffers<>(node, null, null);

        // When
        Geometry result = leaf.geometry();

        // Then
        assertEquals(Geometries.rectangle(0.0, 0.0, 10.0, 10.0), result);
    }

    @Test
    public void testGeometryWithBoundsFloat() {
        // Given
        Node_ node = mock(Node_.class);
        Bounds_ bounds = mock(Bounds_.class);
        BoxFloat_ boxFloat = mock(BoxFloat_.class);

        when(node.mbb()).thenReturn(bounds);
        when(bounds.type()).thenReturn(BoundsType_.BoundsFloat);
        when(bounds.boxFloat()).thenReturn(boxFloat);
        when(boxFloat.minX()).thenReturn(0.0f);
        when(boxFloat.minY()).thenReturn(0.0f);
        when(boxFloat.maxX()).thenReturn(10.0f);
        when(boxFloat.maxY()).thenReturn(10.0f);

        LeafFlatBuffers<Object, Geometry> leaf = new LeafFlatBuffers<>(node, null, null);

        // When
        Geometry result = leaf.geometry();

        // Then
        assertEquals(Geometries.rectangle(0.0, 0.0, 10.0, 10.0), result);
    }
}
