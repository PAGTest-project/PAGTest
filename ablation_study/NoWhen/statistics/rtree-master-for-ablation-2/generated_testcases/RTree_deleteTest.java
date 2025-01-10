
package com.github.davidmoten.rtree;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.davidmoten.rtree.geometry.Geometry;

import rx.Observable;
import rx.functions.Func2;

@RunWith(MockitoJUnitRunner.class)
public class RTree_deleteTest {

    @Mock
    private RTree<Object, Geometry> mockTree;

    @Mock
    private Entry<Object, Geometry> mockEntry1;

    @Mock
    private Entry<Object, Geometry> mockEntry2;

    @Test
    public void testDelete() {
        // Given
        List<Entry<Object, Geometry>> entries = Arrays.asList(mockEntry1, mockEntry2);
        Observable<Entry<Object, Geometry>> observableEntries = Observable.from(entries);

        RTree<Object, Geometry> resultTree1 = new RTree<Object, Geometry>(Optional.empty(), 0, null);
        RTree<Object, Geometry> resultTree2 = new RTree<Object, Geometry>(Optional.empty(), 0, null);

        when(mockTree.delete(mockEntry1, true)).thenReturn(resultTree1);
        when(mockTree.delete(mockEntry2, true)).thenReturn(resultTree2);

        // When
        Observable<RTree<Object, Geometry>> result = mockTree.delete(observableEntries, true);

        // Then
        result.toList().subscribe(trees -> {
            assertEquals(2, trees.size());
            assertEquals(resultTree1, trees.get(0));
            assertEquals(resultTree2, trees.get(1));
            verify(mockTree, times(1)).delete(mockEntry1, true);
            verify(mockTree, times(1)).delete(mockEntry2, true);
        });
    }
}
