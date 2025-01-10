
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonTransformer_generateTest {

    @Test
    public void testGenerateWithFiniteSequence() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList("item1", "item2").iterator());

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertEquals("[\nitem1,\nitem2\n]", result);
    }

    @Test
    public void testGenerateWithInfiniteSequence() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, null);
        });
        assertTrue(exception.getMessage().contains("The sequence should be finite"));
    }

    @Test
    public void testGenerateWithSingleItem() {
        // Given
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        Iterable<String> input = Arrays.asList("item1");

        // When
        String result = transformer.generate(input, schema);

        // Then
        assertEquals("item1", result);
    }
}
