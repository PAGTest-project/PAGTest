
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Keys_ordinalsTest {

    @Test
    public void testOrdinals() {
        int size = 3;
        List<Type.Key> keys = Keys.ordinals(size);

        assertEquals(size, keys.size());
        for (int i = 0; i < size; i++) {
            assertEquals(new OrdinalKey(i), keys.get(i));
        }
    }
}
