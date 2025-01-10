
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.UnaryOperator;

class TupleType_copyTest {

    @Test
    void testCopyNoDifference() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Type type1 = new MockType("Type1");
        Type type2 = new MockType("Type2");
        TupleType tupleType = new TupleType(ImmutableList.of(type1, type2));
        UnaryOperator<Type> identityTransform = t -> t;

        // When
        TupleType result = tupleType.copy(typeSystem, identityTransform);

        // Then
        assertSame(tupleType, result);
    }

    @Test
    void testCopyWithDifference() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Type type1 = new MockType("Type1");
        Type type2 = new MockType("Type2");
        TupleType tupleType = new TupleType(ImmutableList.of(type1, type2));
        UnaryOperator<Type> transform = t -> new MockType("Transformed" + t.toString());

        // When
        TupleType result = tupleType.copy(typeSystem, transform);

        // Then
        assertNotSame(tupleType, result);
        assertEquals(2, result.argTypes.size());
        assertNotEquals(type1, result.argTypes.get(0));
        assertNotEquals(type2, result.argTypes.get(1));
    }

    private static class MockType implements Type {
        private final String name;

        MockType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Key key() {
            return null;
        }

        @Override
        public <R> R accept(TypeVisitor<R> typeVisitor) {
            return null;
        }
    }
}
