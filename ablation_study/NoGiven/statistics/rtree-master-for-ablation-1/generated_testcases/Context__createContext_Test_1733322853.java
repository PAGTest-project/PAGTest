
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class Context__createContext_Test {

    @Test
    public void testCreateContext_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        int boundsOffset = 1;
        int minChildren = 2;
        int maxChildren = 3;

        // When
        when(builder.startTable(3)).thenReturn(0);
        when(builder.endTable()).thenReturn(42);

        int result = Context_.createContext_(builder, boundsOffset, minChildren, maxChildren);

        // Then
        verify(builder).startTable(3);
        verify(builder).addOffset(0, boundsOffset, 0);
        verify(builder).addInt(1, minChildren, 0);
        verify(builder).addInt(2, maxChildren, 0);
        verify(builder).endTable();
        assertEquals(42, result);
    }
}
