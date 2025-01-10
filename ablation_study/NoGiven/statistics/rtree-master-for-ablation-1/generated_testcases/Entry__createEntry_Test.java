
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class Entry__createEntry_Test {

    @Test
    public void testCreateEntry_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        int geometryOffset = 1;
        int objectOffset = 2;

        // When
        when(builder.startTable(2)).thenReturn(0);
        when(builder.endTable()).thenReturn(3);

        int result = Entry_.createEntry_(builder, geometryOffset, objectOffset);

        // Then
        verify(builder).startTable(2);
        verify(builder).addOffset(0, geometryOffset, 0);
        verify(builder).addOffset(1, objectOffset, 0);
        verify(builder).endTable();
        assertEquals(3, result);
    }
}
