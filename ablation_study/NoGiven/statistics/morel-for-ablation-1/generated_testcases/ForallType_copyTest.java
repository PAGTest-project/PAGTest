
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.function.UnaryOperator;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ForallType_copyTest {

    @Test
    void testCopyReturnsSameInstanceWhenTypeUnchanged() {
        TypeSystem typeSystem = new TypeSystem();
        Type originalType = new DataType("TestType", null, null);
        ForallType forallType = new ForallType(1, originalType);

        ForallType result = forallType.copy(typeSystem, UnaryOperator.identity());

        assertSame(forallType, result);
    }

    @Test
    void testCopyReturnsNewInstanceWhenTypeChanged() {
        TypeSystem typeSystem = new TypeSystem();
        Type originalType = new DataType("TestType", null, null);
        ForallType forallType = new ForallType(1, originalType);

        UnaryOperator<Type> transform = t -> new DataType("NewTestType", null, null);
        ForallType result = forallType.copy(typeSystem, transform);

        assertNotSame(forallType, result);
    }
}
