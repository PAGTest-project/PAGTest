
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pos_describeToTest {
    private Pos pos;

    @BeforeEach
    public void setUp() {
        pos = new Pos("stdIn", 1, 4, 1, 7);
    }

    @Test
    public void testDescribeToWithFile() {
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("stdIn:1.4-1.7", buf.toString());
    }

    @Test
    public void testDescribeToWithoutFile() {
        pos = new Pos("", 1, 4, 1, 7);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("1.4-1.7", buf.toString());
    }

    @Test
    public void testDescribeToSameStartAndEnd() {
        pos = new Pos("stdIn", 1, 4, 1, 4);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("stdIn:1.4", buf.toString());
    }

    @Test
    public void testDescribeToDifferentLines() {
        pos = new Pos("stdIn", 1, 4, 2, 3);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("stdIn:1.4-2.3", buf.toString());
    }

    @Test
    public void testDescribeToSameLineDifferentColumns() {
        pos = new Pos("stdIn", 1, 4, 1, 6);
        StringBuilder buf = new StringBuilder();
        pos.describeTo(buf);
        assertEquals("stdIn:1.4-1.6", buf.toString());
    }
}
