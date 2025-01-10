
package com.github.davidmoten.rtree.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import rx.functions.Func0;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SerializerKryo_writeTest {

    @Test
    public void testWrite() throws IOException {
        // Given
        RTree<String, Geometry> tree = mock(RTree.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Func0<Kryo> kryoFactory = mock(Func0.class);
        Kryo kryo = mock(Kryo.class);
        when(kryoFactory.call()).thenReturn(kryo);

        SerializerKryo<String, Geometry> serializer = new SerializerKryo<>(null, null, kryoFactory);

        // When
        serializer.write(tree, os);

        // Then
        verify(kryoFactory).call();
        verify(kryo, never()).writeObject(any(Output.class), any());
    }
}
