
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class BoxDouble__createBoxDouble_Test {

    @Test
    public void testCreateBoxDouble_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        double minX = 1.0, minY = 2.0, maxX = 3.0, maxY = 4.0;
        when(builder.offset()).thenReturn(32);

        // When
        int offset = BoxDouble_.createBoxDouble_(builder, minX, minY, maxX, maxY);

        // Then
        verify(builder).prep(8, 32);
        verify(builder).putDouble(minX);
        verify(builder).putDouble(minY);
        verify(builder).putDouble(maxX);
        verify(builder).putDouble(maxY);
        assertEquals(32, offset);
    }
}
