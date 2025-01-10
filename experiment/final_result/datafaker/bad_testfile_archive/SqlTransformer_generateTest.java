
package net.datafaker.transformations.sql;

import net.datafaker.sequence.FakeSequence;
import net.datafaker.sequence.FakeStream;
import net.datafaker.transformations.Schema;
import net.datafaker.transformations.Field;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SqlTransformer_generateTest {

    @Test
    void testGenerateWithEmptySchema() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        String result = transformer.generate(List.of(), schema);
        assertEquals("", result);
    }

    @Test
    void testGenerateWithInfiniteFakeSequence() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeSequence<Object> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        assertThrows(IllegalArgumentException.class, () -> transformer.generate(fakeSequence, schema));
    }

    @Test
    void testGenerateWithFakeStream() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeStream<Object> fakeStream = mock(FakeStream.class);
        Stream<Object> stream = Stream.of(new Object());
        when(fakeStream.get()).thenReturn(stream);

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(fakeStream, schema);
        assertNotNull(result);
    }

    @Test
    void testGenerateWithList() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        List<Object> list = List.of(new Object());

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(list, schema);
        assertNotNull(result);
    }

    @Test
    void testGenerateWithBatchMode() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().batch().build();
        List<Object> list = List.of(new Object());

        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(list, schema);
        assertNotNull(result);
    }
}
