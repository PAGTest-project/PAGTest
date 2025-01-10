
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Iterator;

public class JsonTransformer_generateTest {

    @Test
    public void testGenerateFiniteSequence() {
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        Iterable<String> input = Arrays.asList("item1", "item2");
        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[0]);

        String result = transformer.generate(input, schema);
        assertEquals("[\nitem1,\nitem2\n]", result);
    }

    @Test
    public void testGenerateInfiniteSequence() {
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
    }

    @Test
    public void testGenerateSingleItemSequence() {
        JsonTransformer<String> transformer = JsonTransformer.builder().build();
        Iterable<String> input = Arrays.asList("item1");
        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[0]);

        String result = transformer.generate(input, schema);
        assertEquals("item1", result);
    }
}
