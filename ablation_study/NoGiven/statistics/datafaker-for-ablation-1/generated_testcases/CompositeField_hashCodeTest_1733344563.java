
package net.datafaker.transformations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.datafaker.providers.base.AbstractProvider;

public class CompositeField_hashCodeTest {

    @Test
    public void testHashCodeWithNonNullName() {
        CompositeField<AbstractProvider<?>, Object> compositeField = new CompositeField<>("testName", null);
        int expectedHashCode = super.hashCode() * 31 + "testName".hashCode();
        assertEquals(expectedHashCode, compositeField.hashCode());
    }

    @Test
    public void testHashCodeWithNullName() {
        CompositeField<AbstractProvider<?>, Object> compositeField = new CompositeField<>(null, null);
        int expectedHashCode = super.hashCode() * 31;
        assertEquals(expectedHashCode, compositeField.hashCode());
    }
}
