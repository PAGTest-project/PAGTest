
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.SortedMap;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class DataType_copyTest {

    @Test
    void testCopyReturnsSameInstanceWhenArgumentsUnchanged() {
        // Given
        List<Type> arguments = ImmutableList.of(mock(Type.class));
        SortedMap<String, Key> typeConstructors = ImmutableSortedMap.of();
        DataType dataType = new DataType("name", "moniker", arguments, typeConstructors);
        TypeSystem typeSystem = mock(TypeSystem.class);
        UnaryOperator<Type> transform = UnaryOperator.identity();

        // When
        DataType result = dataType.copy(typeSystem, transform);

        // Then
        assertEquals(dataType, result);
    }

    @Test
    void testCopyReturnsNewInstanceWhenArgumentsChanged() {
        // Given
        Type originalType = mock(Type.class);
        Type transformedType = mock(Type.class);
        List<Type> arguments = ImmutableList.of(originalType);
        SortedMap<String, Key> typeConstructors = ImmutableSortedMap.of();
        DataType dataType = new DataType("name", "moniker", arguments, typeConstructors);
        TypeSystem typeSystem = mock(TypeSystem.class);
        UnaryOperator<Type> transform = mock(UnaryOperator.class);
        when(transform.apply(originalType)).thenReturn(transformedType);

        // When
        DataType result = dataType.copy(typeSystem, transform);

        // Then
        assertNotEquals(dataType, result);
        assertEquals(ImmutableList.of(transformedType), result.arguments);
    }
}
