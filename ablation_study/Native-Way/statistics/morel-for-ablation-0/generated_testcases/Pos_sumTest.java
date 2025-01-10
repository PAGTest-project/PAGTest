
package net.hydromatic.morel.ast;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Pos_sumTest {

    @Test
    void testSumWithList() {
        List<Pos> poses = Lists.newArrayList(
                new Pos("file1", 1, 1, 1, 10),
                new Pos("file2", 2, 2, 2, 20)
        );
        Pos result = Pos.sum(poses);
        assertEquals("file2", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(2, result.endLine);
        assertEquals(20, result.endColumn);
    }

    @Test
    void testSumWithIterable() {
        Iterable<Pos> poses = Lists.newArrayList(
                new Pos("file1", 1, 1, 1, 10),
                new Pos("file2", 2, 2, 2, 20)
        );
        Pos result = Pos.sum(poses);
        assertEquals("file2", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(2, result.endLine);
        assertEquals(20, result.endColumn);
    }
}
