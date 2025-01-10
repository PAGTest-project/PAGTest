
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.geometry.Geometry;

public class NonLeafDefault_contextTest {

    private NonLeafDefault<String, Geometry> nonLeaf;
    private Context<String, Geometry> mockContext;

    @Before
    public void setUp() {
        mockContext = new Context<>(0, 0, null, null, null);
        nonLeaf = new NonLeafDefault<>(mock(java.util.List.class), mockContext);
    }

    @Test
    public void testContext() {
        assertEquals(mockContext, nonLeaf.context());
    }
}
