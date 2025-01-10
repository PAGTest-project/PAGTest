
package net.hydromatic.morel.type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class TypeSystem_typeForTest {

    private TypeSystem typeSystem;
    private Map<Type.Key, Type> typeByKey;

    @BeforeEach
    void setUp() {
        typeSystem = new TypeSystem();
        typeByKey = new HashMap<>();
        typeSystem.typeByKey.putAll(typeByKey);
    }

    @Test
    void testTypeFor_ExistingKey() {
        Type.Key key = new Type.Key() {
            @Override
            public Type toType(TypeSystem ts) {
                return new Type() {};
            }
        };
        Type expectedType = new Type() {};
        typeByKey.put(key, expectedType);

        Type result = typeSystem.typeFor(key);
        assertEquals(expectedType, result);
    }

    @Test
    void testTypeFor_NewKey() {
        Type.Key key = new Type.Key() {
            @Override
            public Type toType(TypeSystem ts) {
                return new Type() {};
            }
        };
        Type expectedType = key.toType(typeSystem);

        Type result = typeSystem.typeFor(key);
        assertEquals(expectedType, result);
        assertTrue(typeByKey.containsKey(key));
        assertEquals(expectedType, typeByKey.get(key));
    }
}
