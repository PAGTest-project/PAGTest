
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class YamlTransformer_generateTest {

    @Test
    void testGenerateWithFiniteInput() {
        YamlTransformer<String> transformer = new YamlTransformer<>();
        Schema<String, ?> schema = mock(Schema.class);
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        List<String> inputList = Arrays.asList("item1", "item2");

        String result = transformer.generate(inputList, schema);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGenerateWithInfiniteInput() {
        YamlTransformer<String> transformer = new YamlTransformer<>();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, null);
        });
    }
}
