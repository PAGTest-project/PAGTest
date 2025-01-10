
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class XmlTransformer_generateTest {

    @Test
    void testGenerateFiniteSequence() {
        XmlTransformer<String> transformer = new XmlTransformer<>(false);
        Schema<String, ?> schema = mock(Schema.class);
        Field<String, ?> field = mock(Field.class);
        when(schema.getFields()).thenReturn(new Field[]{field});
        when(field.transform(any())).thenReturn("value");

        List<String> input = Arrays.asList("item1", "item2");
        String result = transformer.generate(input, schema);

        assertEquals("value" + XmlTransformer.LINE_SEPARATOR + "value", result);
    }

    @Test
    void testGenerateInfiniteSequence() {
        XmlTransformer<String> transformer = new XmlTransformer<>(false);
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
    }
}
