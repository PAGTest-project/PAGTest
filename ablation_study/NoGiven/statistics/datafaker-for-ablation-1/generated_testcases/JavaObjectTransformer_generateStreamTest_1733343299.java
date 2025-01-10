
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaObjectTransformer_generateStreamTest {

    @Test
    void testGenerateStreamWithEmptySource() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Stream<Object> result = transformer.generateStream(null, 5);
        assertTrue(result.findAny().isEmpty());
    }

    @Test
    void testGenerateStreamWithNonEmptySource() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        transformer.from(String.class);
        Schema<Object, ?> schema = Schema.of();
        Stream<Object> result = transformer.generateStream(schema, 5);
        assertEquals(5, result.count());
    }
}
