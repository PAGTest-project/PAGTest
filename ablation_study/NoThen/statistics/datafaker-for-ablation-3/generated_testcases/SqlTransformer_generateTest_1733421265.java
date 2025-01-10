
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
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        String result = transformer.generate(List.of("input"), schema);
        assertEquals("", result);
    }

    @Test
    public void testGenerateWithInfiniteFakeSequence() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        FakeSequence<String> fakeSequence = mock(FakeSequence.class);
        when(fakeSequence.isInfinite()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            transformer.generate(fakeSequence, mock(Schema.class));
        });
    }

    @Test
    public void testGenerateWithFakeStream() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        FakeStream<String> fakeStream = mock(FakeStream.class);
        Stream<String> stream = Stream.of("input1", "input2");
        when(fakeStream.get()).thenReturn(stream);

        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(fakeStream, schema);
        assertEquals("generateSeparatedStatements result", result); // Replace with actual expected result
    }

    @Test
    public void testGenerateWithList() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        List<String> list = List.of("input1", "input2");

        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(list, schema);
        assertEquals("generateSeparatedStatements result", result); // Replace with actual expected result
    }

    @Test
    public void testGenerateWithBatchMode() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().batch().build();
        List<String> list = List.of("input1", "input2");

        Schema<String, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[]{mock(Field.class)});

        String result = transformer.generate(list, schema);
        assertEquals("generateBatchModeStatements result", result); // Replace with actual expected result
    }
}
