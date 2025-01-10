
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Pos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompileException_describeToTest {

    @Test
    public void testDescribeToWarning() {
        Pos pos = new Pos("file.txt", 1, 1, 1, 10);
        CompileException exception = new CompileException("Test message", true, pos);
        StringBuilder buf = new StringBuilder();
        exception.describeTo(buf);
        assertEquals("file.txt:1:1:1:10 Warning: Test message", buf.toString());
    }

    @Test
    public void testDescribeToError() {
        Pos pos = new Pos("file.txt", 1, 1, 1, 10);
        CompileException exception = new CompileException("Test message", false, pos);
        StringBuilder buf = new StringBuilder();
        exception.describeTo(buf);
        assertEquals("file.txt:1:1:1:10 Error: Test message", buf.toString());
    }
}
