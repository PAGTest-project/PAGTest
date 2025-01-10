
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.davidmoten.rtree.geometry.Geometry;

import rx.Observable;
import rx.functions.Func2;

public class RTree_deleteTest {

    @Mock
    private RTree<String, Geometry> mockTree;

    @Mock
    private Entry<String, Geometry> mockEntry;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDelete() {
        // Given
        List<Entry<String, Geometry>> entries = Arrays.asList(mockEntry);
        Observable<Entry<String, Geometry>> observableEntries = Observable.from(entries);
        boolean all = true;

        // When
        when(mockTree.delete(mockEntry, all)).thenReturn(mockTree);
        Observable<RTree<String, Geometry>> result = mockTree.delete(observableEntries, all);

        // Then
        result.forEach(tree -> assertEquals(mockTree, tree));
        verify(mockTree).delete(mockEntry, all);
    }
}
