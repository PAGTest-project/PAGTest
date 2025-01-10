
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class PointDouble__createPointDouble_Test {

    @Test
    public void testCreatePointDouble_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        double x = 1.0;
        double y = 2.0;

        // When
        when(builder.prep(8, 16)).thenReturn(builder);
        when(builder.putDouble(anyDouble())).thenReturn(builder);
        when(builder.offset()).thenReturn(16);

        int offset = PointDouble_.createPointDouble_(builder, x, y);

        // Then
        verify(builder).prep(8, 16);
        verify(builder).putDouble(y);
        verify(builder).putDouble(x);
        assertEquals(16, offset);
    }
}
