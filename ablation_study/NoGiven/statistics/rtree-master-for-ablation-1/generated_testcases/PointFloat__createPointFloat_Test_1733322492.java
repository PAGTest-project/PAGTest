
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class PointFloat__createPointFloat_Test {

    @Test
    public void testCreatePointFloat_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        float x = 1.0f;
        float y = 2.0f;

        // When
        when(builder.offset()).thenReturn(8);
        int offset = PointFloat_.createPointFloat_(builder, x, y);

        // Then
        verify(builder).prep(4, 8);
        verify(builder).putFloat(x);
        verify(builder).putFloat(y);
        assertEquals(8, offset);
    }
}
