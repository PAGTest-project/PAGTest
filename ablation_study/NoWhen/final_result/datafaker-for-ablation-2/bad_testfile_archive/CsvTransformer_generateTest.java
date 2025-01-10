
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

class CsvTransformer_generateTest {

    @Test
    void testGenerateWithFiniteSequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList("value1", "value2").iterator());

        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[]{});

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGenerateWithInfiniteSequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        Schema<String, ?> schema = mock(Schema.class);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, schema);
        });
    }

    @Test
    void testGenerateWithEmptySequence() {
        // Given
        CsvTransformer<String> transformer = CsvTransformer.<String>builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.iterator()).thenReturn(Arrays.asList().iterator());

        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[]{});

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertEquals("", result);
    }
}
