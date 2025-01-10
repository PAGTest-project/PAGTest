
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
    public void testToStringWithInitialValue() {
        String expected = "OutParameter{sqlType=4, javaType=" + Number.class.getName() + ", value=42}";
        assertEquals(expected, parameter.toString());
    }

    @Test
    public void testToStringAfterSetValue() {
        parameter.setValue(100);
        String expected = "OutParameter{sqlType=4, javaType=" + Number.class.getName() + ", value=100}";
        assertEquals(expected, parameter.toString());
    }

    @Test
    public void testToStringWithNullValue() {
        parameter.setValue(null);
        String expected = "OutParameter{sqlType=4, javaType=" + Number.class.getName() + ", value=null}";
        assertEquals(expected, parameter.toString());
    }
}
