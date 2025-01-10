
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_primitiveToWrapperTest {

    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testPrimitiveToWrapperWithNull() {
        Class<?> result = fakeValuesService.primitiveToWrapper(null);
        assertNull(result);
    }

    @Test
    public void testPrimitiveToWrapperWithPrimitive() {
        Class<?> result = fakeValuesService.primitiveToWrapper(int.class);
        assertEquals(Integer.class, result);
    }

    @Test
    public void testPrimitiveToWrapperWithNonPrimitive() {
        Class<?> result = fakeValuesService.primitiveToWrapper(String.class);
        assertEquals(String.class, result);
    }
}
