
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstWriter_infixTest {

    @Test
    void testInfix_OpApplyWithAstId() {
        // Given
        AstWriter writer = new AstWriter();
        Ast.Id a0 = mock(Ast.Id.class);
        when(a0.op).thenReturn(Op.ID);
        when(a0.name).thenReturn("opName");
        Op op2 = mock(Op.class);
        when(op2.left).thenReturn(1);
        when(Op.BY_OP_NAME.get("opName")).thenReturn(op2);
        Ast.Tuple a1 = mock(Ast.Tuple.class);
        when(a1.args).thenReturn(List.of(mock(Ast.Exp.class), mock(Ast.Exp.class)));
        Ast.InfixCall call = mock(Ast.InfixCall.class);
        when(call.unparse(writer, 0, 0)).thenReturn(writer);
        whenNew(Ast.InfixCall.class).withArguments(Pos.ZERO, op2, any(), any()).thenReturn(call);

        // When
        AstWriter result = writer.infix(0, a0, Op.APPLY, a1, 0);

        // Then
        assertEquals(writer, result);
    }

    @Test
    void testInfix_OpApplyWithCoreId() {
        // Given
        AstWriter writer = new AstWriter();
        Core.Id a0 = mock(Core.Id.class);
        when(a0.op).thenReturn(Op.ID);
        when(a0.idPat).thenReturn(mock(Core.IdPat.class));
        when(a0.idPat.name).thenReturn("opName");
        Op op2 = mock(Op.class);
        when(op2.left).thenReturn(1);
        when(Op.BY_OP_NAME.get("opName")).thenReturn(op2);
        Core.Tuple a1 = mock(Core.Tuple.class);
        when(a1.args).thenReturn(List.of(mock(Core.Exp.class), mock(Core.Exp.class)));

        // When
        AstWriter result = writer.infix(0, a0, Op.APPLY, a1, 0);

        // Then
        assertEquals(writer, result);
    }

    @Test
    void testInfix_Parenthesize() {
        // Given
        AstWriter writer = new AstWriter();
        AstNode a0 = mock(AstNode.class);
        when(a0.op).thenReturn(Op.ID);
        AstNode a1 = mock(AstNode.class);
        Op op = mock(Op.class);
        when(op.left).thenReturn(1);
        when(op.right).thenReturn(1);

        // When
        AstWriter result = writer.infix(2, a0, op, a1, 0);

        // Then
        assertEquals(writer, result);
    }
}
