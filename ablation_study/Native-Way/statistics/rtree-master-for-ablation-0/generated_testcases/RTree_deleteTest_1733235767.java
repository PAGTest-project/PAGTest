
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import rx.Observable;
import rx.functions.Func2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RTree_deleteTest {

    @Test
    public void testDelete() {
        // Given
        RTree<String, Geometry> tree = mock(RTree.class);
        Entry<String, Geometry> entry = mock(Entry.class);
        Observable<Entry<String, Geometry>> entries = Observable.just(entry);
        boolean all = true;

        when(tree.delete(entry, all)).thenReturn(tree);

        // When
        Observable<RTree<String, Geometry>> result = tree.delete(entries, all);

        // Then
        result.forEach(rTree -> assertEquals(tree, rTree));
        verify(tree).delete(entry, all);
    }
}
