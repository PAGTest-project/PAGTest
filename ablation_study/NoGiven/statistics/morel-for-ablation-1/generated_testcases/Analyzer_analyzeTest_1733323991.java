
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.AstNode;
import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class Analyzer_analyzeTest {

    @Test
    void testAnalyzeWithNonRecValDecl() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Environment env = mock(Environment.class);
        Core.NonRecValDecl node = mock(Core.NonRecValDecl.class);
        Core.NamedPat pat = mock(Core.NamedPat.class);
        when(node.pat).thenReturn(pat);

        // When
        Analysis result = Analyzer.analyze(typeSystem, env, node);

        // Then
        assertTrue(result.map.containsKey(pat));
    }

    @Test
    void testAnalyzeWithNonNonRecValDecl() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Environment env = mock(Environment.class);
        AstNode node = mock(AstNode.class);

        // When
        Analysis result = Analyzer.analyze(typeSystem, env, node);

        // Then
        assertTrue(result.map.isEmpty());
    }
}
