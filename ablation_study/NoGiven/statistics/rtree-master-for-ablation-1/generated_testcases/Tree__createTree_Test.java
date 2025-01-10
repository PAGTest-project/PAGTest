
package com.github.davidmoten.rtree.fbs.generated;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import com.google.flatbuffers.FlatBufferBuilder;

public class Tree__createTree_Test {

    @Test
    public void testCreateTree_() {
        // Given
        FlatBufferBuilder builder = mock(FlatBufferBuilder.class);
        int contextOffset = 1;
        int rootOffset = 2;
        long size = 100L;

        // When
        when(builder.startTable(3)).thenReturn(0);
        when(builder.endTable()).thenReturn(42);

        int result = Tree_.createTree_(builder, contextOffset, rootOffset, size);

        // Then
        verify(builder).startTable(3);
        verify(builder).addOffset(0, contextOffset, 0);
        verify(builder).addOffset(1, rootOffset, 0);
        verify(builder).addInt(2, (int)size, 0);
        verify(builder).endTable();
        assertEquals(42, result);
    }
}
