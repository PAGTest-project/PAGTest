
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import java.util.function.Function;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

class SimpleField_equalsTest {

    @Test
    void testEquals_SameInstance() {
        SimpleField<String, Integer> field = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s));
        assertTrue(field.equals(field));
    }

    @Test
    void testEquals_DifferentType() {
        SimpleField<String, Integer> field = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s));
        assertFalse(field.equals("not a SimpleField"));
    }

    @Test
    void testEquals_DifferentName() {
        SimpleField<String, Integer> field1 = new SimpleField<>("name1", (Function<String, Integer>) s -> Integer.parseInt(s));
        SimpleField<String, Integer> field2 = new SimpleField<>("name2", (Function<String, Integer>) s -> Integer.parseInt(s));
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEquals_DifferentTransform() {
        SimpleField<String, Integer> field1 = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s));
        SimpleField<String, Integer> field2 = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s) + 1);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEquals_DifferentSupplier() {
        SimpleField<String, Integer> field1 = new SimpleField<>("name", (Supplier<Integer>) () -> 1);
        SimpleField<String, Integer> field2 = new SimpleField<>("name", (Supplier<Integer>) () -> 2);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEquals_SameFields() {
        SimpleField<String, Integer> field1 = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s), (Supplier<Integer>) () -> 1);
        SimpleField<String, Integer> field2 = new SimpleField<>("name", (Function<String, Integer>) s -> Integer.parseInt(s), (Supplier<Integer>) () -> 1);
        assertTrue(field1.equals(field2));
    }
}
