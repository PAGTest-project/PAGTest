
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.List;

import static org.mockito.Mockito.*;

public class NonLeafDefault_searchWithoutBackpressureTest {

    @Test
    public void testSearchWithoutBackpressure() {
        // Given
        Context<String, Geometry> context = mock(Context.class);
        NonLeafDefault<String, Geometry> nonLeaf = new NonLeafDefault<>(mock(List.class), context);
        Func1<Geometry, Boolean> criterion = mock(Func1.class);
        Subscriber<Entry<String, Geometry>> subscriber = mock(Subscriber.class);

        // When
        nonLeaf.searchWithoutBackpressure(criterion, subscriber);

        // Then
        verify(NonLeafHelper.class);
    }
}
