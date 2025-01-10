
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.AstNode;
import net.hydromatic.morel.type.Type;
import net.hydromatic.morel.type.TypeVar;
import net.hydromatic.morel.util.Unifier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypeMap_typeIsVariableTest {

    @Test
    void testTypeIsVariable_WithTypeVar() {
        // Given
        TypeMap typeMap = mock(TypeMap.class);
        AstNode node = mock(AstNode.class);
        Unifier.Variable variable = mock(Unifier.Variable.class);
        TypeVar typeVar = mock(TypeVar.class);

        when(typeMap.nodeTypeTerms.get(node)).thenReturn(variable);
        when(typeMap.termToType(variable)).thenReturn(typeVar);

        // When
        boolean result = typeMap.typeIsVariable(node);

        // Then
        assertTrue(result);
    }

    @Test
    void testTypeIsVariable_WithNonVariableTerm() {
        // Given
        TypeMap typeMap = mock(TypeMap.class);
        AstNode node = mock(AstNode.class);
        Unifier.Term term = mock(Unifier.Term.class);

        when(typeMap.nodeTypeTerms.get(node)).thenReturn(term);

        // When
        boolean result = typeMap.typeIsVariable(node);

        // Then
        assertFalse(result);
    }

    @Test
    void testTypeIsVariable_WithProgressiveType() {
        // Given
        TypeMap typeMap = mock(TypeMap.class);
        AstNode node = mock(AstNode.class);
        Unifier.Variable variable = mock(Unifier.Variable.class);
        Type progressiveType = mock(Type.class);

        when(typeMap.nodeTypeTerms.get(node)).thenReturn(variable);
        when(typeMap.termToType(variable)).thenReturn(progressiveType);
        when(progressiveType.isProgressive()).thenReturn(true);

        // When
        boolean result = typeMap.typeIsVariable(node);

        // Then
        assertTrue(result);
    }
}
