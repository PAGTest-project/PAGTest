
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaObjectTransformer_generateStreamTest {

    @Test
    public void testGenerateStreamWithEmptySourceClazz() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Stream<Object> result = transformer.generateStream(null, 10);
        assertTrue(result.findAny().isEmpty());
    }

    @Test
    public void testGenerateStreamWithNonEmptySourceClazz() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        transformer.from(String.class);
        Schema<Object, ?> schema = new Schema<Object, Object>() {
            @Override
            public Object apply(Object source) {
                return source;
            }
        };
        Stream<Object> result = transformer.generateStream(schema, 10);
        assertEquals(10, result.count());
    }
}
