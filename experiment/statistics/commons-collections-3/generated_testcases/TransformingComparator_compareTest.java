
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformingComparator_compareTest {

    private Transformer<String, Integer> transformer;
    private Comparator<Integer> decoratedComparator;
    private TransformingComparator<String, Integer> transformingComparator;

    @BeforeEach
    public void setUp() {
        transformer = mock(Transformer.class);
        decoratedComparator = mock(Comparator.class);
        transformingComparator = new TransformingComparator<>(transformer, decoratedComparator);
    }

    @Test
    public void testCompare() {
        String obj1 = "1";
        String obj2 = "2";
        Integer transformedObj1 = 1;
        Integer transformedObj2 = 2;

        when(transformer.apply(obj1)).thenReturn(transformedObj1);
        when(transformer.apply(obj2)).thenReturn(transformedObj2);
        when(decoratedComparator.compare(transformedObj1, transformedObj2)).thenReturn(-1);

        int result = transformingComparator.compare(obj1, obj2);

        assertEquals(-1, result);
    }
}
