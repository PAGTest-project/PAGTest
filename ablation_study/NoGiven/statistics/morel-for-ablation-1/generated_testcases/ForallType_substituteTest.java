
package net.hydromatic.morel.type;

import com.google.common.collect.Maps;
import net.hydromatic.morel.ast.Op;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ForallType_substituteTest {

    @Test
    void testSubstituteDataType() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        DataType dataType = mock(DataType.class);
        when(dataType.op()).thenReturn(Op.DATA_TYPE);
        when(dataType.name).thenReturn("TestDataType");
        when(dataType.typeConstructors).thenReturn(new TreeMap<>());

        ForallType forallType = new ForallType(1, dataType);

        Key expectedKey = mock(Key.class);
        Type expectedType = mock(Type.class);
        when(Keys.datatype(anyString(), anyList(), anyMap())).thenReturn(expectedKey);
        when(typeSystem.typeFor(expectedKey)).thenReturn(expectedType);

        // When
        Type result = forallType.substitute(typeSystem, Collections.emptyList());

        // Then
        assertEquals(expectedType, result);
    }

    @Test
    void testSubstituteFunctionType() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type functionType = mock(Type.class);
        when(functionType.op()).thenReturn(Op.FUNCTION_TYPE);

        ForallType forallType = new ForallType(1, functionType);

        Type expectedType = mock(Type.class);
        when(functionType.substitute(eq(typeSystem), anyList())).thenReturn(expectedType);

        // When
        Type result = forallType.substitute(typeSystem, Collections.emptyList());

        // Then
        assertEquals(expectedType, result);
    }

    @Test
    void testSubstituteDefaultCase() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type unknownType = mock(Type.class);
        when(unknownType.op()).thenReturn(Op.UNKNOWN);
        when(unknownType.toString()).thenReturn("UnknownType");

        ForallType forallType = new ForallType(1, unknownType);

        // When & Then
        assertThrows(AssertionError.class, () -> forallType.substitute(typeSystem, Collections.emptyList()));
    }
}
