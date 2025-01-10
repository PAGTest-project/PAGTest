
package net.datafaker.transformations.sql;

import net.datafaker.sequence.FakeSequence;
import net.datafaker.sequence.FakeStream;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SqlTransformer_generateTest {

    @Test
    public void testGenerateWithEmptySchema() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        String result = transformer.generate(List.of(), schema);
        assertEquals("", result);
    }

    @Test
    public void testGenerateWithInfiniteFakeSequence() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeSequence<Object> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> transformer.generate(fakeSequence, mock(Schema.class)));
    }

    @Test
    public void testGenerateWithFakeStream() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeStream<Object> fakeStream = mock(FakeStream.class);
        Stream<Object> stream = Stream.of(new Object());
        when(fakeStream.get()).thenReturn(stream);

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{});

        String result = transformer.generate(fakeStream, schema);
        assertEquals("", result);
    }

    @Test
    public void testGenerateWithBatchMode() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().batch().build();
        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{});

        String result = transformer.generate(List.of(new Object()), schema);
        assertEquals("", result);
    }
}
