
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.function.UnaryOperator;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

class FnType_copyTest {

    @Test
    void testCopyReturnsSameInstanceWhenTypesAreUnchanged() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type paramType = mock(Type.class);
        Type resultType = mock(Type.class);
        FnType fnType = new FnType(paramType, resultType);
        UnaryOperator<Type> transform = mock(UnaryOperator.class);

        when(paramType.copy(eq(typeSystem), eq(transform))).thenReturn(paramType);
        when(resultType.copy(eq(typeSystem), eq(transform))).thenReturn(resultType);

        // When
        FnType result = fnType.copy(typeSystem, transform);

        // Then
        assertSame(fnType, result);
    }

    @Test
    void testCopyReturnsNewInstanceWhenTypesAreChanged() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type paramType = mock(Type.class);
        Type resultType = mock(Type.class);
        FnType fnType = new FnType(paramType, resultType);
        UnaryOperator<Type> transform = mock(UnaryOperator.class);

        Type newParamType = mock(Type.class);
        Type newResultType = mock(Type.class);

        when(paramType.copy(eq(typeSystem), eq(transform))).thenReturn(newParamType);
        when(resultType.copy(eq(typeSystem), eq(transform))).thenReturn(newResultType);
        when(typeSystem.fnType(eq(newParamType), eq(newResultType))).thenReturn(new FnType(newParamType, newResultType));

        // When
        FnType result = fnType.copy(typeSystem, transform);

        // Then
        assertNotSame(fnType, result);
        assertSame(newParamType, result.paramType);
        assertSame(newResultType, result.resultType);
    }
}
