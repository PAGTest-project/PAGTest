
package net.datafaker.transformations.sql;

import net.datafaker.transformations.Schema;
import net.datafaker.transformations.SimpleField;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SqlTransformer_generateStreamTest {

    @Test
    public void testGenerateStreamWithEmptySchema() {
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().build();
        Schema<Object, ?> schema = Schema.of();
        Stream<CharSequence> result = transformer.generateStream(schema, 10);
        assertTrue(result.findAny().isEmpty());
    }

    @Test
    public void testGenerateStreamWithoutBatchMode() {
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().build();
        Schema<Object, ?> schema = Schema.of(new SimpleField<>("field1", o -> "value1"));
        Stream<CharSequence> result = transformer.generateStream(schema, 2);
        assertEquals(2, result.count());
    }

    @Test
    public void testGenerateStreamWithBatchMode() {
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().batch(2).build();
        Schema<Object, ?> schema = Schema.of(new SimpleField<>("field1", o -> "value1"));
        Stream<CharSequence> result = transformer.generateStream(schema, 3);
        assertEquals(2, result.count());
    }
}
