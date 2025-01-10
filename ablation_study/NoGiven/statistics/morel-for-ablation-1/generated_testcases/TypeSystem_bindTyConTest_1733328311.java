
package net.hydromatic.morel.type;

import net.hydromatic.morel.ast.CoreBuilder;
import net.hydromatic.morel.eval.Codes;
import net.hydromatic.morel.util.ComparableSingletonList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TypeSystem_bindTyConTest {

    @Test
    public void testBindTyCon_DummyType() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        DataType dataType = mock(DataType.class);
        when(dataType.typeConstructors(typeSystem)).thenReturn(mock(java.util.Map.class));
        when(dataType.typeConstructors(typeSystem).get("tyConName")).thenReturn(DummyType.INSTANCE);

        // When
        Binding result = typeSystem.bindTyCon(dataType, "tyConName");

        // Then
        Binding expected = Binding.of(CoreBuilder.core.idPat(dataType, "tyConName", 0),
                Codes.constant(ComparableSingletonList.of("tyConName")));
        assertEquals(expected.id, result.id);
        assertEquals(expected.value, result.value);
    }

    @Test
    public void testBindTyCon_NonDummyType() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        DataType dataType = mock(DataType.class);
        Type mockType = mock(Type.class);
        when(dataType.typeConstructors(typeSystem)).thenReturn(mock(java.util.Map.class));
        when(dataType.typeConstructors(typeSystem).get("tyConName")).thenReturn(mockType);
        when(typeSystem.wrap(dataType, any(Type.class))).thenReturn(mockType);

        // When
        Binding result = typeSystem.bindTyCon(dataType, "tyConName");

        // Then
        Binding expected = Binding.of(CoreBuilder.core.idPat(mockType, "tyConName", 0),
                Codes.tyCon(dataType, "tyConName"));
        assertEquals(expected.id, result.id);
        assertEquals(expected.value, result.value);
    }
}
