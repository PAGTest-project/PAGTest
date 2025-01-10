
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TypeVisitor_visitTest {

    @Test
    public void testVisitTupleType() {
        // Given
        TypeVisitor<String> visitor = new TypeVisitor<String>() {
            @Override
            public String visit(TypeVar typeVar) {
                return "TypeVar";
            }

            @Override
            public String visit(TupleType tupleType) {
                StringBuilder sb = new StringBuilder();
                for (Type argType : tupleType.argTypes) {
                    sb.append(argType.accept(this));
                }
                return sb.toString();
            }
        };

        Type typeVar1 = mock(TypeVar.class);
        Type typeVar2 = mock(TypeVar.class);
        when(typeVar1.accept(visitor)).thenReturn("TypeVar1");
        when(typeVar2.accept(visitor)).thenReturn("TypeVar2");

        TupleType tupleType = new TupleType(Arrays.asList(typeVar1, typeVar2));

        // When
        String result = visitor.visit(tupleType);

        // Then
        assertEquals("TypeVar1TypeVar2", result);
    }
}
