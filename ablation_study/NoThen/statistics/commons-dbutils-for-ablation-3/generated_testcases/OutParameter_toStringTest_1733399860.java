
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
        String expected = "OutParameter{sqlType=4, javaType=" + Number.class.getName() + ", value=null}";
        assertEquals(expected, parameter.toString());
    }

    @Test
    public void testToStringWithValue() {
        parameter.setValue(123);
        String expected = "OutParameter{sqlType=4, javaType=" + Number.class.getName() + ", value=123}";
        assertEquals(expected, parameter.toString());
    }
}
