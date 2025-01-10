
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvTransformer_getStartStreamTest {

    @Test
    public void testGetStartStream() {
        CsvTransformer<Object> transformer = new CsvTransformer<>(";", '"', false);
        String result = transformer.getStartStream(null);
        assertEquals("", result);
    }
}
