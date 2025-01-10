
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Iterator;

class CsvTransformer_generateTest {

    @Test
    void testGenerateWithFiniteSequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        Iterator<String> iterator = Arrays.asList("value1", "value2").iterator();
        when(fakeSequence.iterator()).thenReturn(iterator);

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGenerateWithInfiniteSequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
        assertTrue(exception.getMessage().contains("The sequence should be finite"));
    }

    @Test
    void testGenerateWithEmptySequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        Schema<String, ?> schema = mock(Schema.class);
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList().iterator());

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
