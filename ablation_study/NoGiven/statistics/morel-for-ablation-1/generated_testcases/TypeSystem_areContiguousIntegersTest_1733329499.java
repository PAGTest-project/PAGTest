
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class TypeSystem_areContiguousIntegersTest {

    @Test
    public void testAreContiguousIntegers_Contiguous() {
        Iterable<String> strings = Arrays.asList("1", "2", "3");
        assertTrue(TypeSystem.areContiguousIntegers(strings));
    }

    @Test
    public void testAreContiguousIntegers_NonContiguous() {
        Iterable<String> strings = Arrays.asList("1", "3", "4");
        assertFalse(TypeSystem.areContiguousIntegers(strings));
    }
}
