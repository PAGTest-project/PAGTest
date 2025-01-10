
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class YamlTransformer_generateTest {

    @Test
    void testGenerateWithFiniteSequence() {
        // Given
        YamlTransformer<String> transformer = new YamlTransformer<>();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        List<String> inputList = Arrays.asList("item1", "item2");
        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[0]);

        // When
        String result = transformer.generate(fakeSequence, schema);

        // Then
        assertEquals("", result);
    }

    @Test
    void testGenerateWithInfiniteSequence() {
        // Given
        YamlTransformer<String> transformer = new YamlTransformer<>();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
    }
}
