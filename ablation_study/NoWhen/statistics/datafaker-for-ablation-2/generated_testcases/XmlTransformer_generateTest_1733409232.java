
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
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        List<String> inputList = Arrays.asList("item1", "item2");

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
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, schema);
        });
    }
}
