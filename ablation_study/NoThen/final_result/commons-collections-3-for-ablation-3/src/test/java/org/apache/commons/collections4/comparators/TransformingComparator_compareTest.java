
package org.apache.commons.collections4.comparators;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransformingComparator_compareTest {

    @Test
    public void testCompare() {
        // Given
        Transformer<Integer, String> transformer = mock(Transformer.class);
        Comparator<String> decorated = mock(Comparator.class);
        TransformingComparator<Integer, String> comparator = new TransformingComparator<>(transformer, decorated);

        Integer obj1 = 1;
        Integer obj2 = 2;
        String transformedValue1 = "1";
        String transformedValue2 = "2";

        when(transformer.apply(obj1)).thenReturn(transformedValue1);
        when(transformer.apply(obj2)).thenReturn(transformedValue2);
        when(decorated.compare(transformedValue1, transformedValue2)).thenReturn(-1);

        // When
        int result = comparator.compare(obj1, obj2);

        // Then
        assertEquals(-1, result);
        verify(transformer, times(1)).apply(obj1);
        verify(transformer, times(1)).apply(obj2);
        verify(decorated, times(1)).compare(transformedValue1, transformedValue2);
    }
}
