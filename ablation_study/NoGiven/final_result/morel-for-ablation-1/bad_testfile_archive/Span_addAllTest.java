
package net.hydromatic.morel.parse;

import net.hydromatic.morel.ast.AstNode;
import net.hydromatic.morel.ast.Pos;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Span_addAllTest {

    @Test
    public void testAddAll() {
        // Given
        Span span = Span.of();
        AstNode node1 = new AstNode(Pos.ZERO, null) {};
        AstNode node2 = new AstNode(Pos.ZERO, null) {};

        // When
        span.addAll(Arrays.asList(node1, node2));

        // Then
        assertEquals(2, span.posList.size());
    }

    @Test
    public void testAddAllWithEmptyList() {
        // Given
        Span span = Span.of();

        // When
        span.addAll(Collections.emptyList());

        // Then
        assertEquals(0, span.posList.size());
    }
}
