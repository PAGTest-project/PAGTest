
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeHandler_createObjectTest {

    @Test
    public void testCreateObjectSuccess() throws ParseException {
        Object obj = TypeHandler.createObject("java.lang.String");
        assertEquals(String.class, obj.getClass());
    }

    @Test
    public void testCreateObjectFailure() {
        assertThrows(ParseException.class, () -> {
            TypeHandler.createObject("NonExistentClass");
        });
    }
}
