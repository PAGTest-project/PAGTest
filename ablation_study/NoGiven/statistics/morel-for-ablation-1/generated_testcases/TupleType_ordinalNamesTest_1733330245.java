
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TupleType_ordinalNamesTest {

    @Test
    public void testOrdinalNames() {
        List<String> result = TupleType.ordinalNames(3);
        assertEquals(3, result.size());
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("3", result.get(2));
    }
}
