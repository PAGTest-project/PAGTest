
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeSystem_typeVariablesTest {

    @Test
    public void testTypeVariables() {
        TypeSystem typeSystem = new TypeSystem();
        int size = 3;
        List<TypeVar> typeVars = typeSystem.typeVariables(size);

        assertEquals(size, typeVars.size());
        for (int i = 0; i < size; i++) {
            assertEquals(typeSystem.typeVariable(i), typeVars.get(i));
        }
    }
}
