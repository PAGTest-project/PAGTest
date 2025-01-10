
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class LineFloat__createLineFloat_Test {

    @Test
    public void testCreateLineFloat_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        when(builder.offset()).thenReturn(16);

        // When
        int offset = LineFloat_.createLineFloat_(builder, 1.0f, 2.0f, 3.0f, 4.0f);

        // Then
        verify(builder).prep(4, 16);
        verify(builder).putFloat(1.0f);
        verify(builder).putFloat(2.0f);
        verify(builder).putFloat(3.0f);
        verify(builder).putFloat(4.0f);
        assertEquals(16, offset);
    }
}
