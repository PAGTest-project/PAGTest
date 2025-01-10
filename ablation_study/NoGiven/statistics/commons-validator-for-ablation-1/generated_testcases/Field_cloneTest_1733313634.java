
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Field_cloneTest {

    @Test
    void testClone() {
        // Given
        Field original = new Field();
        original.args = new Map[]{new HashMap<String, Arg>() {{
            put("key1", new Arg());
        }}};
        original.hVars = new FastHashMap();
        original.hMsgs = new FastHashMap();

        // When
        Field cloned = (Field) original.clone();

        // Then
        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertNotSame(original.args, cloned.args);
        assertNotSame(original.hVars, cloned.hVars);
        assertNotSame(original.hMsgs, cloned.hMsgs);
        assertEquals(original.args.length, cloned.args.length);
        assertNotNull(cloned.args[0]);
        assertNotSame(original.args[0], cloned.args[0]);
        assertEquals(original.args[0].size(), cloned.args[0].size());
    }
}
