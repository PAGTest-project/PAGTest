
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pos_describeToTest {

    @Test
    public void testDescribeTo_SingleLine() {
        Pos pos = new Pos("file.txt", 1, 2, 1, 3);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2-1.3", buf.toString());
    }

    @Test
    public void testDescribeTo_MultiLine() {
        Pos pos = new Pos("file.txt", 1, 2, 2, 3);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2-2.3", buf.toString());
    }

    @Test
    public void testDescribeTo_EmptyFile() {
        Pos pos = new Pos("", 1, 2, 1, 3);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("1.2-1.3", buf.toString());
    }

    @Test
    public void testDescribeTo_SameStartAndEnd() {
        Pos pos = new Pos("file.txt", 1, 2, 1, 2);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("file.txt:1.2", buf.toString());
    }
}
