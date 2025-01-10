
package org.apache.commons.dbutils.handlers.properties;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringEnumPropertyHandler_applyTest {

    @Test
    public void testApply_ValidEnum() {
        StringEnumPropertyHandler handler = new StringEnumPropertyHandler();
        Object result = handler.apply(TestEnum.class, "VALUE1");
        assertEquals(TestEnum.VALUE1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApply_InvalidEnum() {
        StringEnumPropertyHandler handler = new StringEnumPropertyHandler();
        handler.apply(TestEnum.class, "INVALID");
    }

    private enum TestEnum {
        VALUE1, VALUE2
    }
}
