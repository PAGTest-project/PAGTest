
package net.hydromatic.morel.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeSystem_lookupInternalTest {

    private TypeSystem typeSystem;

    @BeforeEach
    void setUp() {
        typeSystem = new TypeSystem();
        typeSystem.setInternal("int"); // Ensure internalTypeByName is populated
    }

    @Test
    void testLookupInternal_ExistingType() {
        Type result = typeSystem.lookupInternal("int");
        assertNotNull(result);
    }

    @Test
    void testLookupInternal_NonExistingType() {
        assertThrows(AssertionError.class, () -> typeSystem.lookupInternal("unknown"));
    }
}
