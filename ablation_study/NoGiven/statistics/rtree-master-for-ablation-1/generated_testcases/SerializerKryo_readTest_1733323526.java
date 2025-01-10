
package com.github.davidmoten.rtree.kryo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.InternalStructure;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;

public class SerializerKryo_readTest {

    private SerializerKryo<Object, Geometry> serializerKryo;
    private InputStream inputStream;
    private InternalStructure structure;

    @Before
    public void setUp() {
        serializerKryo = new SerializerKryo<>(null, null, () -> new Kryo());
        inputStream = new ByteArrayInputStream(new byte[0]);
        structure = mock(InternalStructure.class);
    }

    @Test
    public void testRead() throws IOException {
        RTree<Object, Geometry> result = serializerKryo.read(inputStream, 0, structure);
        assertNotNull(result);
    }
}
