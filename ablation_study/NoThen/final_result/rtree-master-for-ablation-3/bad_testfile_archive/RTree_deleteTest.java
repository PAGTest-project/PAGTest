
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

    private RTree<Object, Geometry> mockTree;

    @Mock
    private Entry<Object, Geometry> mockEntry;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockTree = spy(new RTree<Object, Geometry>() {
            @Override
            public RTree<Object, Geometry> delete(Entry<Object, Geometry> entry, boolean all) {
                return this;
            }
        });
    }

    @Test
    public void testDelete() {
        // Given
        List<Entry<Object, Geometry>> entries = Arrays.asList(mockEntry);
        Observable<Entry<Object, Geometry>> observableEntries = Observable.from(entries);

        // When
        Observable<RTree<Object, Geometry>> result = mockTree.delete(observableEntries, true);

        // Then
        result.forEach(tree -> assertEquals(mockTree, tree));
    }
}
