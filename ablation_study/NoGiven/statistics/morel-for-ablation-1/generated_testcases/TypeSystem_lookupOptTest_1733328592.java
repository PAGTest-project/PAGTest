
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeSystem_lookupOptTest {

    @Test
    void testLookupOpt_TypeFound() {
        TypeSystem typeSystem = new TypeSystem();
        Type expectedType = PrimitiveType.INT;
        typeSystem.typeByName.put("int", expectedType);

        Type actualType = typeSystem.lookupOpt("int");

        assertEquals(expectedType, actualType);
    }

    @Test
    void testLookupOpt_TypeNotFound() {
        TypeSystem typeSystem = new TypeSystem();

        Type actualType = typeSystem.lookupOpt("unknown");

        assertNull(actualType);
    }
}
