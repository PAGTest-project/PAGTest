
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class TypeSystem_applyTest {

    @Test
    void testApplyForallType() {
        TypeSystem typeSystem = new TypeSystem();
        ForallType forallType = new ForallType(2, new DataType("TestDataType", "TestMoniker", Arrays.asList(), null));
        List<Type> types = Arrays.asList(typeSystem.typeVariable(0), typeSystem.typeVariable(1));

        Type result = typeSystem.apply(forallType, types);

        assertNotNull(result);
        assertTrue(result instanceof DataType);
    }

    @Test
    void testApplyDataType() {
        TypeSystem typeSystem = new TypeSystem();
        DataType dataType = new DataType("TestDataType", "TestMoniker", Arrays.asList(), null);
        List<Type> types = Arrays.asList(typeSystem.typeVariable(0), typeSystem.typeVariable(1));

        Type result = typeSystem.apply(dataType, types);

        assertNotNull(result);
        assertTrue(result instanceof DataType);
    }

    @Test
    void testApplyThrowsAssertionError() {
        TypeSystem typeSystem = new TypeSystem();
        Type invalidType = new Type() {
            @Override
            public Key key() {
                return null;
            }

            @Override
            public <R> R accept(TypeVisitor<R> typeVisitor) {
                return null;
            }
        };
        List<Type> types = Arrays.asList(typeSystem.typeVariable(0), typeSystem.typeVariable(1));

        assertThrows(AssertionError.class, () -> typeSystem.apply(invalidType, types));
    }
}
