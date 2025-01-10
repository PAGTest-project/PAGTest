
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.AstNode;
import net.hydromatic.morel.util.Unifier;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeMap_toStringTest {

    @Test
    public void testToString() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Map<AstNode, Unifier.Term> nodeTypeTerms = new HashMap<>();
        Unifier.Substitution substitution = new Unifier.Substitution();
        TypeMap typeMap = new TypeMap(typeSystem, nodeTypeTerms, substitution);

        AstNode node1 = new AstNode(null, null);
        Unifier.Term term1 = new Unifier.Variable("var1");
        nodeTypeTerms.put(node1, term1);

        AstNode node2 = new AstNode(null, null);
        Unifier.Term term2 = new Unifier.Variable("var2");
        nodeTypeTerms.put(node2, term2);

        // When
        String result = typeMap.toString();

        // Then
        String expected = "terms:\n" +
                "var1: AstNode{null, null}\n" +
                "var2: AstNode{null, null}\n" +
                "substitution:\n";
        assertEquals(expected, result);
    }
}
