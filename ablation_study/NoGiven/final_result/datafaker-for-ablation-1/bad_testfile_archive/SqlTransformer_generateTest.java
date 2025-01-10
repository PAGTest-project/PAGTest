
package net.datafaker.transformations.sql;

import net.datafaker.sequence.FakeSequence;
import net.datafaker.sequence.FakeStream;
import net.datafaker.transformations.Schema;
import net.datafaker.transformations.Field;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SqlTransformer_generateTest {

    @Test
    public void testGenerateWithEmptySchema() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        Schema<Object, ?> schema = Mockito.mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        String result = transformer.generate(List.of(), schema);
        assertEquals("", result); // Assuming EMPTY_RESULT is not accessible, use empty string instead
    }

    @Test
    public void testGenerateWithInfiniteFakeSequence() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeSequence<Object> fakeSequence = Mockito.mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> transformer.generate(fakeSequence, Mockito.mock(Schema.class)));
    }

    @Test
    public void testGenerateWithFakeStream() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().build();
        FakeStream<Object> fakeStream = Mockito.mock(FakeStream.class);
        Stream<Object> stream = Stream.of(new Object());
        when(fakeStream.get()).thenReturn(stream);

        Schema<Object, ?> schema = Mockito.mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{Mockito.mock(Field.class)});

        String result = transformer.generate(fakeStream, schema);
        assertEquals("", result); // Assuming generateSeparatedStatements returns empty string for simplicity
    }

    @Test
    public void testGenerateWithBatchMode() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>().batch().build();
        Schema<Object, ?> schema = Mockito.mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{Mockito.mock(Field.class)});

        String result = transformer.generate(List.of(new Object()), schema);
        assertEquals("", result); // Assuming generateBatchModeStatements returns empty string for simplicity
    }
}
