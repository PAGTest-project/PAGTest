
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class XmlTransformer_generateTest {

    @Test
    void testGenerateFiniteSequence() {
        // Given
        XmlTransformer<String> transformer = new XmlTransformer<>(false);
        Schema<String, ?> schema = mock(Schema.class);
        Iterable<String> fakeSequence = mock(Iterable.class);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList("item1", "item2").iterator());

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGenerateInfiniteSequence() {
        // Given
        XmlTransformer<String> transformer = new XmlTransformer<>(false);
        Schema<String, ?> schema = mock(Schema.class);
        Iterable<String> fakeSequence = mock(Iterable.class);
        when(fakeSequence.iterator()).thenReturn(new InfiniteIterator());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, schema);
        });
    }

    private static class InfiniteIterator implements Iterator<String> {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public String next() {
            return "item";
        }
    }
}
