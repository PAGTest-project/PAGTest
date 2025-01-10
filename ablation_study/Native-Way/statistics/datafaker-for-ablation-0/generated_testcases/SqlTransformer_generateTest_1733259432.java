
package net.datafaker.transformations.sql;

import net.datafaker.sequence.FakeSequence;
import net.datafaker.sequence.FakeStream;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SqlTransformer_generateTest {

    @Test
    public void testGenerateWithEmptySchema() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        Schema<String, ?> schema = Schema.of();
        Iterable<String> input = Arrays.asList("data1", "data2");

        String result = transformer.generate(input, schema);
        assertEquals("", result);
    }

    @Test
    public void testGenerateWithInfiniteFakeSequence() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        Schema<String, ?> schema = Schema.of();
        FakeSequence<String> infiniteSequence = new FakeSequence<String>(null, 0, 0, null, 0) {
            @Override
            public boolean isInfinite() {
                return true;
            }

            @Override
            public List<String> get() {
                return null;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> transformer.generate(infiniteSequence, schema));
    }

    @Test
    public void testGenerateWithFakeStream() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        Schema<String, ?> schema = Schema.of();
        FakeStream<String> fakeStream = new FakeStream<String>(null, 0, 0, null, 0) {
            @Override
            public Stream<String> get() {
                return Stream.of("data1", "data2");
            }
        };

        String result = transformer.generate(fakeStream, schema);
        assertEquals("", result); // Assuming generateSeparatedStatements returns empty string for simplicity
    }

    @Test
    public void testGenerateWithList() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().build();
        Schema<String, ?> schema = Schema.of();
        List<String> input = Arrays.asList("data1", "data2");

        String result = transformer.generate(input, schema);
        assertEquals("", result); // Assuming generateSeparatedStatements returns empty string for simplicity
    }

    @Test
    public void testGenerateWithBatchMode() {
        SqlTransformer<String> transformer = new SqlTransformer.SqlTransformerBuilder<String>().batch().build();
        Schema<String, ?> schema = Schema.of();
        List<String> input = Arrays.asList("data1", "data2");

        String result = transformer.generate(input, schema);
        assertEquals("", result); // Assuming generateBatchModeStatements returns empty string for simplicity
    }
}
