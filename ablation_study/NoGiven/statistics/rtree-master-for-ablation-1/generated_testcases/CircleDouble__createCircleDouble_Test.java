
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class CircleDouble__createCircleDouble_Test {

    @Test
    public void testCreateCircleDouble_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        when(builder.offset()).thenReturn(42);

        // When
        int offset = CircleDouble_.createCircleDouble_(builder, 1.0, 2.0, 3.0);

        // Then
        verify(builder).prep(8, 24);
        verify(builder).putDouble(3.0);
        verify(builder).putDouble(2.0);
        verify(builder).putDouble(1.0);
        assertEquals(42, offset);
    }
}
