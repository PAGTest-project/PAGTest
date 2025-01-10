
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Schema_equalsTest {

    @Test
    void testEquals_SameInstance() {
        Schema<String, String> schema = Schema.of();
        assertTrue(schema.equals(schema));
    }

    @Test
    void testEquals_DifferentInstanceSameFields() {
        Schema<String, String> schema1 = Schema.of(new SimpleField<String, String>());
        Schema<String, String> schema2 = Schema.of(new SimpleField<String, String>());
        assertTrue(schema1.equals(schema2));
    }

    @Test
    void testEquals_DifferentInstanceDifferentFields() {
        Schema<String, String> schema1 = Schema.of(new SimpleField<String, String>());
        Schema<String, String> schema2 = Schema.of(new SimpleField<String, String>(), new SimpleField<String, String>());
        assertFalse(schema1.equals(schema2));
    }

    @Test
    void testEquals_NotInstanceOfSchema() {
        Schema<String, String> schema = Schema.of();
        assertFalse(schema.equals("not a schema"));
    }
}
