
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Ast;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TypeResolver_deduceTypeTest {

    @Test
    public void testDeduceType_SingleAttempt() {
        // Given
        Environment env = mock(Environment.class);
        Ast.Decl decl = mock(Ast.Decl.class);
        TypeSystem typeSystem = mock(TypeSystem.class);
        TypeResolver typeResolver = new TypeResolver(typeSystem);
        TypeResolver.Resolved expectedResolved = mock(TypeResolver.Resolved.class);

        when(typeSystem.expandCount.get()).thenReturn(0);
        when(typeResolver.deduceType_(env, decl)).thenReturn(expectedResolved);

        // When
        TypeResolver.Resolved actualResolved = TypeResolver.deduceType(env, decl, typeSystem);

        // Then
        assertEquals(expectedResolved, actualResolved);
    }

    @Test
    public void testDeduceType_MultipleAttempts() {
        // Given
        Environment env = mock(Environment.class);
        Ast.Decl decl = mock(Ast.Decl.class);
        TypeSystem typeSystem = mock(TypeSystem.class);
        TypeResolver typeResolver = new TypeResolver(typeSystem);
        TypeResolver.Resolved expectedResolved = mock(TypeResolver.Resolved.class);

        when(typeSystem.expandCount.get()).thenReturn(1, 2);
        when(typeResolver.deduceType_(env, decl)).thenReturn(expectedResolved);

        // When
        TypeResolver.Resolved actualResolved = TypeResolver.deduceType(env, decl, typeSystem);

        // Then
        assertEquals(expectedResolved, actualResolved);
    }
}
