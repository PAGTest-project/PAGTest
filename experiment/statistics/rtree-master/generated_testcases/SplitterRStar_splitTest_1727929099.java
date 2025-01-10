
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.davidmoten.rtree.geometry.HasGeometry;
import com.github.davidmoten.rtree.geometry.ListPair;

@RunWith(MockitoJUnitRunner.class)
public class SplitterRStar_splitTest {

    private SplitterRStar splitter;

    @Mock
    private HasGeometry mockGeometry1;

    @Mock
    private HasGeometry mockGeometry2;

    @Before
    public void setUp() {
        splitter = new SplitterRStar();
    }

    @Test
    public void testSplit() {
        List<HasGeometry> items = new ArrayList<>();
        items.add(mockGeometry1);
        items.add(mockGeometry2);

        ListPair<HasGeometry> expectedPair = new ListPair<>(items.subList(0, 1), items.subList(1, 2));

        ListPair<HasGeometry> result = splitter.split(items, 1);

        assertEquals(expectedPair.group1(), result.group1());
        assertEquals(expectedPair.group2(), result.group2());
    }
}
