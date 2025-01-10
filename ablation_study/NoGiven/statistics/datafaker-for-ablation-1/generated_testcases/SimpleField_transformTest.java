
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.util.function.Function;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

class SimpleField_transformTest {

    @Test
    void testTransformWithTransformFunction() {
        Function<String, Integer> transform = Integer::parseInt;
        SimpleField<String, Integer> field = new SimpleField<>("test", transform);

        assertEquals(42, field.transform("42"));
    }

    @Test
    void testTransformWithSupplier() {
        Supplier<Integer> supplier = () -> 42;
        SimpleField<String, Integer> field = new SimpleField<>("test", supplier);

        assertEquals(42, field.transform(null));
    }

    @Test
    void testTransformWithNullInputAndNoSupplier() {
        Function<String, Integer> transform = Integer::parseInt;
        SimpleField<String, Integer> field = new SimpleField<>("test", transform);

        assertThrows(IllegalArgumentException.class, () -> field.transform(null));
    }
}
