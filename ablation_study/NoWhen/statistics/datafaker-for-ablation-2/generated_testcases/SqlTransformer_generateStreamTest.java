
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
        // Given
        Schema<Object, CharSequence> schema = Schema.of();
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().build();

        // When
        Stream<CharSequence> result = transformer.generateStream(schema, 10);

        // Then
        assertTrue(result.findAny().isEmpty());
    }

    @Test
    public void testGenerateStreamWithBatchMode() {
        // Given
        Schema<Object, CharSequence> schema = Schema.of(new SimpleField<>("field1", (Object o) -> "value1"));
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().batch(2).build();

        // When
        Stream<CharSequence> result = transformer.generateStream(schema, 3);

        // Then
        assertEquals(2, result.count());
    }

    @Test
    public void testGenerateStreamWithoutBatchMode() {
        // Given
        Schema<Object, CharSequence> schema = Schema.of(new SimpleField<>("field1", (Object o) -> "value1"));
        SqlTransformer<Object> transformer = SqlTransformer.<Object>builder().build();

        // When
        Stream<CharSequence> result = transformer.generateStream(schema, 3);

        // Then
        assertEquals(3, result.count());
    }
}
