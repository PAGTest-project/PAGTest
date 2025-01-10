
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FakeValuesService_primitiveToWrapperTest {

    @Test
    void testPrimitiveToWrapper() {
        // Given
        Class<?> primitiveClass = int.class;
        Class<?> nonPrimitiveClass = String.class;

        // When
        Class<?> wrappedPrimitive = FakeValuesService.primitiveToWrapper(primitiveClass);
        Class<?> nonWrappedClass = FakeValuesService.primitiveToWrapper(nonPrimitiveClass);

        // Then
        assertEquals(Integer.class, wrappedPrimitive);
        assertEquals(nonPrimitiveClass, nonWrappedClass);
    }

    @Test
    void testPrimitiveToWrapperWithNull() {
        // Given
        Class<?> nullClass = null;

        // When
        Class<?> result = FakeValuesService.primitiveToWrapper(nullClass);

        // Then
        assertNull(result);
    }
}
