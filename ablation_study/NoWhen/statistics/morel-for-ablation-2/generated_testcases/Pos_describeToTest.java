
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pos_describeToTest {

    @Test
    void testDescribeToWithEmptyFile() {
        Pos pos = new Pos("", 1, 2, 1, 2);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("1.2", buf.toString());
    }

    @Test
    void testDescribeToWithNonEmptyFile() {
        Pos pos = new Pos("file.txt", 1, 2, 1, 2);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2", buf.toString());
    }

    @Test
    void testDescribeToWithDifferentEndColumn() {
        Pos pos = new Pos("file.txt", 1, 2, 1, 4);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2-1.4", buf.toString());
    }

    @Test
    void testDescribeToWithDifferentEndLine() {
        Pos pos = new Pos("file.txt", 1, 2, 2, 2);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2-2.2", buf.toString());
    }

    @Test
    void testDescribeToWithDifferentEndLineAndColumn() {
        Pos pos = new Pos("file.txt", 1, 2, 2, 4);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2-2.4", buf.toString());
    }
}
