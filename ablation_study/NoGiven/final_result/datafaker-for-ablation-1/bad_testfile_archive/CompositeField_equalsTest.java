
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import net.datafaker.providers.base.AbstractProvider;

class CompositeField_equalsTest {

    @Test
    void testEquals_SameInstance() {
        CompositeField<AbstractProvider<?>, Object> field = new CompositeField<>("name", null);
        assertTrue(field.equals(field));
    }

    @Test
    void testEquals_DifferentClass() {
        CompositeField<AbstractProvider<?>, Object> field = new CompositeField<>("name", null);
        assertFalse(field.equals(new Object()));
    }

    @Test
    void testEquals_DifferentName() {
        CompositeField<AbstractProvider<?>, Object> field1 = new CompositeField<>("name1", null);
        CompositeField<AbstractProvider<?>, Object> field2 = new CompositeField<>("name2", null);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEquals_SameName() {
        CompositeField<AbstractProvider<?>, Object> field1 = new CompositeField<>("name", null);
        CompositeField<AbstractProvider<?>, Object> field2 = new CompositeField<>("name", null);
        assertTrue(field1.equals(field2));
    }

    @Test
    void testEquals_SuperNotEqual() {
        CompositeField<AbstractProvider<?>, Object> field1 = new CompositeField<>("name", new Field[]{new SimpleField("field1")});
        CompositeField<AbstractProvider<?>, Object> field2 = new CompositeField<>("name", new Field[]{new SimpleField("field2")});
        assertFalse(field1.equals(field2));
    }
}
