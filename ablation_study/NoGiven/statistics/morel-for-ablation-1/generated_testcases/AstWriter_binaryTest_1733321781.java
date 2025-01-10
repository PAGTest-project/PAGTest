
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstWriter_binaryTest {

    @Test
    void testBinary() {
        // Given
        AstWriter writer = new AstWriter();
        AstNode mockNode1 = mock(AstNode.class);
        AstNode mockNode2 = mock(AstNode.class);

        // When
        writer.binary("left", mockNode1, "mid", mockNode2, 0);

        // Then
        verify(mockNode1).unparse(writer, 0, 0);
        verify(mockNode2).unparse(writer, 0, 0);
        assertEquals("leftmid", writer.toString());
    }
}
