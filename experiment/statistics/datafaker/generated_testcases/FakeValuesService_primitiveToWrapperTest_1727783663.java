
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FakeValuesService_primitiveToWrapperTest {

    @Test
    void testPrimitiveToWrapper_Primitive() {
        Class<?> result = FakeValuesService.primitiveToWrapper(int.class);
        assertEquals(Integer.class, result);
    }

    @Test
    void testPrimitiveToWrapper_NonPrimitive() {
        Class<?> result = FakeValuesService.primitiveToWrapper(String.class);
        assertEquals(String.class, result);
    }

    @Test
    void testPrimitiveToWrapper_Null() {
        Class<?> result = FakeValuesService.primitiveToWrapper(null);
        assertNull(result);
    }
}
