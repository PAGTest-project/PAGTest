
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class CircleFloat__createCircleFloat_Test {

    @Test
    public void testCreateCircleFloat_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        when(builder.offset()).thenReturn(12345);

        // When
        int offset = CircleFloat_.createCircleFloat_(builder, 1.0f, 2.0f, 3.0f);

        // Then
        verify(builder).prep(4, 12);
        verify(builder).putFloat(3.0f);
        verify(builder).putFloat(2.0f);
        verify(builder).putFloat(1.0f);
        assertEquals(12345, offset);
    }
}
