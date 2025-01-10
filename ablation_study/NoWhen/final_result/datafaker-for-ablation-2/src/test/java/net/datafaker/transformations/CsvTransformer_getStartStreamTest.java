
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTransformer_getStartStreamTest {

    @Test
    public void testGetStartStream() {
        // Given
        CsvTransformer<Object> transformer = CsvTransformer.builder().header(false).build();
        Schema<Object, ?> schema = Schema.of();

        // When
        String result = transformer.getStartStream(schema);

        // Then
        assertEquals("", result);
    }
}
