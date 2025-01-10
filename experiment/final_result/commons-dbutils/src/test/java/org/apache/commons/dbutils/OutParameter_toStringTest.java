
package org.apache.commons.dbutils;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class OutParameter_toStringTest {
    private OutParameter<Number> parameter;

    @Before
    public void setUp() {
        parameter = new OutParameter<>(java.sql.Types.INTEGER, Number.class);
    }

    @Test
    public void testToString() {
        parameter.setValue(42);
        assertEquals("OutParameter{sqlType=4, javaType=class java.lang.Number, value=42}", parameter.toString());
    }
}
