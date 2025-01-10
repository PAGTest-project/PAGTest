
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

class YamlTransformer_generateTest {

    @Test
    void testGenerateWithFiniteInput() {
        YamlTransformer<String> transformer = new YamlTransformer<>();
        Schema<String, ?> schema = mock(Schema.class);
        Iterable<String> inputList = Arrays.asList("item1", "item2");

        String result = transformer.generate(inputList, schema);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGenerateWithInfiniteInput() {
        YamlTransformer<String> transformer = new YamlTransformer<>();
        Iterable<String> infiniteInput = mock(Iterable.class);
        when(infiniteInput.iterator()).thenThrow(new IllegalArgumentException("The sequence should be finite"));

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(infiniteInput, null);
        });
    }
}
