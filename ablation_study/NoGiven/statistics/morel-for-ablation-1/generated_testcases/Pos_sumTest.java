
package net.hydromatic.morel.ast;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Pos_sumTest {

    @Test
    void testSumWithList() {
        Pos pos1 = new Pos("file1", 1, 1, 2, 2);
        Pos pos2 = new Pos("file2", 3, 3, 4, 4);
        List<Pos> poses = Lists.newArrayList(pos1, pos2);

        Pos result = Pos.sum(poses);

        assertEquals(new Pos("file2", 1, 1, 4, 4), result);
    }

    @Test
    void testSumWithIterable() {
        Pos pos1 = new Pos("file1", 1, 1, 2, 2);
        Pos pos2 = new Pos("file2", 3, 3, 4, 4);
        Iterable<Pos> poses = Lists.newArrayList(pos1, pos2);

        Pos result = Pos.sum(poses);

        assertEquals(new Pos("file2", 1, 1, 4, 4), result);
    }
}
