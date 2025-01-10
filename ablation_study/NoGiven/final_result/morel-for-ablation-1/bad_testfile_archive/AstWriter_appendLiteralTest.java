
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AstWriter_appendLiteralTest {

    @Test
    void testAppendLiteralString() {
        AstWriter writer = new AstWriter();
        writer.appendLiteral("test\\\"string");
        assertEquals("\"test\\\\\\\"string\"", writer.toString());
    }

    @Test
    void testAppendLiteralCharacter() {
        AstWriter writer = new AstWriter();
        writer.appendLiteral('\\');
        assertEquals("#\"\\\\\"", writer.toString());
    }

    @Test
    void testAppendLiteralBigDecimal() {
        AstWriter writer = new AstWriter();
        writer.appendLiteral(new BigDecimal("-123.45"));
        assertEquals("~123.45", writer.toString());
    }

    @Test
    void testAppendLiteralBuiltIn() {
        AstWriter writer = new AstWriter();
        BuiltIn builtIn = new BuiltIn();
        builtIn.mlName = "find";
        builtIn.structure = "List";
        writer.appendLiteral(builtIn);
        assertEquals("#find List", writer.toString());
    }

    @Test
    void testAppendLiteralOther() {
        AstWriter writer = new AstWriter();
        writer.appendLiteral(123);
        assertEquals("123", writer.toString());
    }
}
