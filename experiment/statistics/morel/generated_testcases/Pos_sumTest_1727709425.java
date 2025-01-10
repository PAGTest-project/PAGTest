
package net.hydromatic.morel.ast;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pos_sumTest {

    @Test
    public void testSumWithList() {
        List<Pos> poses = Lists.newArrayList(
                new Pos("file1", 1, 1, 2, 2),
                new Pos("file2", 3, 3, 4, 4)
        );
        Pos result = Pos.sum(poses);
        assertEquals("file1", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(4, result.endLine);
        assertEquals(4, result.endColumn);
    }

    @Test
    public void testSumWithIterable() {
        Iterable<Pos> poses = Lists.newArrayList(
                new Pos("file1", 1, 1, 2, 2),
                new Pos("file2", 3, 3, 4, 4)
        );
        Pos result = Pos.sum(poses);
        assertEquals("file1", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(4, result.endLine);
        assertEquals(4, result.endColumn);
    }
}
