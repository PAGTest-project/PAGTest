
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaObjectTransformer_generateStreamTest {

    @Test
    public void testGenerateStreamWithEmptySource() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        Stream<Object> result = transformer.generateStream(null, 5);
        assertTrue(result.count() == 0);
    }

    @Test
    public void testGenerateStreamWithNonEmptySource() {
        JavaObjectTransformer transformer = new JavaObjectTransformer();
        transformer.from(String.class);
        Stream<Object> result = transformer.generateStream(null, 5);
        assertEquals(5, result.count());
    }
}
