
package org.apache.commons.dbutils;

import static org.junit.Assert.assertEquals;
import java.sql.Types;
import org.junit.Before;
import org.junit.Test;

public class OutParameter_toStringTest {
    private OutParameter<Number> parameter;

    @Before
    public void setUp() {
        parameter = new OutParameter<>(Types.INTEGER, Number.class);
    }

    @Test
    public void testToStringWithNullValue() {
        String expected = "OutParameter{sqlType=" + Types.INTEGER + ", javaType=" + Number.class + ", value=null}";
        assertEquals(expected, parameter.toString());
    }

    @Test
    public void testToStringWithValue() {
        Number value = 42;
        parameter.setValue(value);
        String expected = "OutParameter{sqlType=" + Types.INTEGER + ", javaType=" + Number.class + ", value=" + value + '}';
        assertEquals(expected, parameter.toString());
    }
}
