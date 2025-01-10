
package net.datafaker.transformations;

import net.datafaker.sequence.FakeSequence;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JavaObjectTransformer_generateTest {

    @Test
    void testGenerateWithFakeSequenceFinite() {
        FakeSequence<Object> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(false);
        when(fakeSequence.get()).thenReturn(Arrays.asList("item1", "item2"));

        Schema<Object, ?> schema = mock(Schema.class);
        JavaObjectTransformer transformer = new JavaObjectTransformer();

        Collection<Object> result = transformer.generate(fakeSequence, schema);

        assertEquals(2, result.size());
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
    }

    @Test
    void testGenerateWithFakeSequenceInfinite() {
        FakeSequence<Object> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        Schema<Object, ?> schema = mock(Schema.class);
        JavaObjectTransformer transformer = new JavaObjectTransformer();

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, schema);
        });
    }

    @Test
    void testGenerateWithIterable() {
        Iterable<Object> iterable = Arrays.asList("item1", "item2");
        Schema<Object, ?> schema = mock(Schema.class);
        JavaObjectTransformer transformer = new JavaObjectTransformer();

        Collection<Object> result = transformer.generate(iterable, schema);

        assertEquals(2, result.size());
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
    }
}
