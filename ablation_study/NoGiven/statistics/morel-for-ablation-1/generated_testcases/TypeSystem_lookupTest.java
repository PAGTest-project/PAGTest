
package net.hydromatic.morel.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeSystem_lookupTest {

    private TypeSystem typeSystem;

    @BeforeEach
    void setUp() {
        typeSystem = new TypeSystem();
    }

    @Test
    void testLookup_ExistingType() {
        // Given
        String name = "int";
        PrimitiveType expectedType = PrimitiveType.INT;
        typeSystem.typeByName.put(name, expectedType);

        // When
        Type actualType = typeSystem.lookup(name);

        // Then
        assertEquals(expectedType, actualType);
    }

    @Test
    void testLookup_NonExistingType() {
        // Given
        String name = "unknown";

        // When & Then
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            typeSystem.lookup(name);
        });
        assertEquals("unknown type: " + name, exception.getMessage());
    }
}
