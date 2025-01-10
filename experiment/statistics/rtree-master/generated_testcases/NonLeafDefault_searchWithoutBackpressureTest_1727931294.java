
package com.github.davidmoten.rtree.internal;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Before;
import org.junit.Test;
import rx.Subscriber;
import rx.functions.Func1;

import static org.mockito.Mockito.*;

public class NonLeafDefault_searchWithoutBackpressureTest {

    private NonLeafDefault<String, Geometry> nonLeafDefault;
    private Func1<Geometry, Boolean> criterion;
    private Subscriber<Entry<String, Geometry>> subscriber;

    @Before
    public void setUp() {
        // Mocking dependencies
        criterion = mock(Func1.class);
        subscriber = mock(Subscriber.class);
        nonLeafDefault = mock(NonLeafDefault.class);
    }

    @Test
    public void testSearchWithoutBackpressure() {
        // Given
        doCallRealMethod().when(nonLeafDefault).searchWithoutBackpressure(any(), any());

        // When
        nonLeafDefault.searchWithoutBackpressure(criterion, subscriber);

        // Then
        verify(nonLeafDefault).searchWithoutBackpressure(criterion, subscriber);
        verify(nonLeafDefault, times(1)).searchWithoutBackpressure(criterion, subscriber);
    }
}
