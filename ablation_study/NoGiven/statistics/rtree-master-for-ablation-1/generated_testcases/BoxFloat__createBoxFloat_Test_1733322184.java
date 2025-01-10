
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class BoxFloat__createBoxFloat_Test {

    @Test
    public void testCreateBoxFloat_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        float minX = 1.0f;
        float minY = 2.0f;
        float maxX = 3.0f;
        float maxY = 4.0f;

        // When
        int offset = BoxFloat_.createBoxFloat_(builder, minX, minY, maxX, maxY);

        // Then
        verify(builder).prep(4, 16);
        verify(builder).putFloat(maxY);
        verify(builder).putFloat(maxX);
        verify(builder).putFloat(minY);
        verify(builder).putFloat(minX);
        verify(builder, times(1)).offset();
        assertEquals(0, offset); // Assuming offset is 0 when mocked
    }
}
