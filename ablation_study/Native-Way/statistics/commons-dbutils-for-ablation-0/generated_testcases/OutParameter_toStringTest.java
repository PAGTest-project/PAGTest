
package org.apache.commons.dbutils;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class OutParameter_toStringTest {
    private OutParameter<Number> parameter;

    @Before
    public void setUp() {
        parameter = new OutParameter<>(java.sql.Types.INTEGER, Number.class, 42);
    }

    @Test
    public void testToString() {
        String expected = "OutParameter{sqlType=4, javaType=class java.lang.Number, value=42}";
        assertEquals(expected, parameter.toString());
    }

    @Test
    public void testToStringWithNullValue() {
        parameter = new OutParameter<>(java.sql.Types.INTEGER, Number.class, null);
        String expected = "OutParameter{sqlType=4, javaType=class java.lang.Number, value=null}";
        assertEquals(expected, parameter.toString());
    }
}
