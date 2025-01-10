
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.ObjIntConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ord_forEachTest {

    @Test
    public void testForEach() {
        List<String> list = Arrays.asList("a", "b", "c");
        StringBuilder result = new StringBuilder();
        ObjIntConsumer<String> consumer = (e, i) -> result.append(i).append(e);

        Ord.forEach(list, consumer);

        assertEquals("0a1b2c", result.toString());
    }
}
