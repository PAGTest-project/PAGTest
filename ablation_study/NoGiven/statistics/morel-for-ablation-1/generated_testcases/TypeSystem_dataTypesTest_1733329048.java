
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import net.hydromatic.morel.util.Pair;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TypeSystem_dataTypesTest {

    @Test
    public void testDataTypes() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Keys.DataTypeKey mockKey1 = mock(Keys.DataTypeKey.class);
        Keys.DataTypeKey mockKey2 = mock(Keys.DataTypeKey.class);
        DataType mockDataType1 = mock(DataType.class);
        DataType mockDataType2 = mock(DataType.class);
        Type.Key mockNameKey1 = mock(Type.Key.class);
        Type.Key mockNameKey2 = mock(Type.Key.class);
        Type.Key mockTypeKey1 = mock(Type.Key.class);
        Type.Key mockTypeKey2 = mock(Type.Key.class);

        when(mockKey1.toType(typeSystem)).thenReturn(mockDataType1);
        when(mockKey2.toType(typeSystem)).thenReturn(mockDataType2);
        when(mockDataType1.name).thenReturn("name1");
        when(mockDataType2.name).thenReturn("name2");
        when(Keys.name(mockDataType1.name)).thenReturn(mockNameKey1);
        when(Keys.name(mockDataType2.name)).thenReturn(mockNameKey2);
        when(mockDataType1.arguments).thenReturn(ImmutableList.of());
        when(mockDataType2.arguments).thenReturn(ImmutableList.of());
        when(mockDataType1.typeConstructors).thenReturn(ImmutableList.of(Pair.of("type1", mockTypeKey1)));
        when(mockDataType2.typeConstructors).thenReturn(ImmutableList.of(Pair.of("type2", mockTypeKey2)));

        List<Keys.DataTypeKey> keys = ImmutableList.of(mockKey1, mockKey2);

        // When
        List<Type> result = typeSystem.dataTypes(keys);

        // Then
        assertEquals(2, result.size());
        assertEquals(mockDataType1, result.get(0));
        assertEquals(mockDataType2, result.get(1));
    }
}
