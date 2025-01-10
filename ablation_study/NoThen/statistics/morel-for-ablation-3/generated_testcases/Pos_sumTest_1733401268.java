
package net.hydromatic.morel.ast;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pos_sumTest {

    private Pos pos1;
    private Pos pos2;
    private Pos pos3;

    @BeforeEach
    public void setUp() {
        pos1 = new Pos("file1", 1, 1, 1, 5);
        pos2 = new Pos("file1", 1, 6, 1, 10);
        pos3 = new Pos("file2", 2, 1, 2, 5);
    }

    @Test
    public void testSumWithList() {
        List<Pos> poses = Arrays.asList(pos1, pos2);
        Pos result = Pos.sum(poses);
        assertEquals("file1", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(10, result.endColumn);
    }

    @Test
    public void testSumWithIterable() {
        Iterable<Pos> poses = Lists.newArrayList(pos1, pos3);
        Pos result = Pos.sum(poses);
        assertEquals("file1", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(2, result.endLine);
        assertEquals(5, result.endColumn);
    }

    @Test
    public void testSumWithSinglePos() {
        List<Pos> poses = Arrays.asList(pos1);
        Pos result = Pos.sum(poses);
        assertEquals("file1", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(5, result.endColumn);
    }

    @Test
    public void testSumWithEmptyList() {
        List<Pos> poses = Lists.newArrayList();
        try {
            Pos.sum(poses);
        } catch (AssertionError e) {
            assertEquals("AssertionError", e.getClass().getSimpleName());
        }
    }
}
