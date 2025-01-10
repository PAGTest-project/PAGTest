
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.function.UnaryOperator;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

class ListType_copyTest {

    @Test
    void testCopySameElementType() {
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type elementType = mock(Type.class);
        ListType listType = new ListType(elementType);

        when(elementType.copy(typeSystem, any())).thenReturn(elementType);

        ListType result = listType.copy(typeSystem, mock(UnaryOperator.class));

        assertSame(listType, result);
    }

    @Test
    void testCopyDifferentElementType() {
        TypeSystem typeSystem = mock(TypeSystem.class);
        Type elementType = mock(Type.class);
        Type newElementType = mock(Type.class);
        ListType listType = new ListType(elementType);

        when(elementType.copy(typeSystem, any())).thenReturn(newElementType);
        when(typeSystem.listType(newElementType)).thenReturn(new ListType(newElementType));

        ListType result = listType.copy(typeSystem, mock(UnaryOperator.class));

        assertNotSame(listType, result);
        assertSame(newElementType, result.elementType);
    }
}
