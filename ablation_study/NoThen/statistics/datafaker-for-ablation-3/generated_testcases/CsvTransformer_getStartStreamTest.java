
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTransformer_getStartStreamTest {

    @Test
    public void testGetStartStream() {
        CsvTransformer<Object> transformer = CsvTransformer.builder().header(false).build();
        String result = transformer.getStartStream(null);
        assertEquals("", result);
    }
}
