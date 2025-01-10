
package net.hydromatic.morel.type;

import net.hydromatic.morel.util.PairList;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.UnaryOperator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecordType_copyTest {

    @Test
    void testCopyNoDifference() {
        // Given
        SortedMap<String, Type> argNameTypes = new TreeMap<>();
        argNameTypes.put("key1", mock(Type.class));
        RecordType recordType = new RecordType(argNameTypes);
        TypeSystem typeSystem = mock(TypeSystem.class);
        UnaryOperator<Type> transform = mock(UnaryOperator.class);

        // When
        when(transform.apply(any(Type.class))).thenAnswer(i -> i.getArgument(0));
        RecordType result = recordType.copy(typeSystem, transform);

        // Then
        assertEquals(recordType, result);
        verify(typeSystem, never()).recordType(any());
    }

    @Test
    void testCopyWithDifference() {
        // Given
        SortedMap<String, Type> argNameTypes = new TreeMap<>();
        Type originalType = mock(Type.class);
        Type transformedType = mock(Type.class);
        argNameTypes.put("key1", originalType);
        RecordType recordType = new RecordType(argNameTypes);
        TypeSystem typeSystem = mock(TypeSystem.class);
        UnaryOperator<Type> transform = mock(UnaryOperator.class);

        // When
        when(transform.apply(originalType)).thenReturn(transformedType);
        when(typeSystem.recordType(any(PairList.class))).thenReturn(new RecordType(argNameTypes));
        RecordType result = recordType.copy(typeSystem, transform);

        // Then
        assertEquals(argNameTypes, result.argNameTypes());
        verify(typeSystem, times(1)).recordType(any());
    }
}
