
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTransformer_getStartStreamTest {

    @Test
    public void testGetStartStream() {
        // Given
        CsvTransformer<Object> transformer = CsvTransformer.builder().header(false).build();

        // When
        String result = transformer.getStartStream(Schema.of(Field.field("test")));

        // Then
        assertEquals("", result);
    }
}
