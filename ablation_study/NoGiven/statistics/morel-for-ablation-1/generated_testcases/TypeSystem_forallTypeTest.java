
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeSystem_forallTypeTest {

    @Test
    void testForallType() {
        TypeSystem typeSystem = new TypeSystem();

        // Given
        int typeCount = 2;
        Function<TypeSystem.ForallHelper, Type> builder = helper -> helper.list(0);

        // When
        Type result = typeSystem.forallType(typeCount, builder);

        // Then
        assertEquals(typeSystem.forallType(typeCount, typeSystem.listType(typeSystem.typeVariable(0))), result);
    }
}
