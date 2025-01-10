
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonTransformer_generateTest {

    @Test
    void testGenerateWithFiniteSequence() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList("item1", "item2").iterator());

        Schema<String, ?> schema = mock(Schema.class);
        when(transformer.apply(any(), eq(schema))).thenReturn("item1", "item2");

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertEquals("[\nitem1,\nitem2\n]", result);
    }

    @Test
    void testGenerateWithInfiniteSequence() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
    }

    @Test
    void testGenerateWithSingleItem() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        Iterable<String> input = Arrays.asList("item1");
        Schema<String, ?> schema = mock(Schema.class);
        when(transformer.apply(any(), eq(schema))).thenReturn("item1");

        // When
        String result = transformer.generate(input, schema);

        // Then
        assertEquals("item1", result);
    }
}
