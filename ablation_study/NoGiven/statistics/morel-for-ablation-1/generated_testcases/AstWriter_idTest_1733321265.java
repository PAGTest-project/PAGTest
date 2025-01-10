
package net.hydromatic.morel.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AstWriter_idTest {

    @Test
    public void testId_withZero() {
        AstWriter writer = new AstWriter();
        writer.id("test", 0);
        assertEquals("test", writer.toString());
    }

    @Test
    public void testId_withPositiveInteger() {
        AstWriter writer = new AstWriter();
        writer.id("test", 5);
        assertEquals("test_5", writer.toString());
    }
}
