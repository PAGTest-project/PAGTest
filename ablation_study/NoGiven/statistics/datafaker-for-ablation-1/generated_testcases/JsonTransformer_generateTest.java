
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class JsonTransformer_generateTest {

    @Test
    public void testGenerateFiniteSequence() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<String, ?> schema = Mockito.mock(Schema.class);
        FakeSequence<String> fakeSequence = Mockito.mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList("item1", "item2").iterator());

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertEquals("[\nitem1,\nitem2\n]", result);
    }

    @Test
    public void testGenerateInfiniteSequence() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        FakeSequence<String> fakeSequence = Mockito.mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, null);
        });
    }

    @Test
    public void testGenerateSingleItem() {
        // Given
        JsonTransformer<Object> transformer = JsonTransformer.builder().build();
        Schema<String, ?> schema = Mockito.mock(Schema.class);
        Iterable<String> input = Arrays.asList("item1");

        // When
        String result = transformer.generate(input, schema);

        // Then
        assertEquals("item1", result);
    }
}
