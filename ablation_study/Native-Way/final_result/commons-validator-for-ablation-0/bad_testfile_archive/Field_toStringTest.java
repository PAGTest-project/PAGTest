
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class Field_toStringTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
        field.key = "testKey";
        field.property = "testProperty";
        field.indexedProperty = "testIndexedProperty";
        field.indexedListProperty = "testIndexedListProperty";
        field.depends = "testDepends";
        field.page = 1;
        field.fieldOrder = 2;
        field.hVars = new HashMap<>();
        field.hVars.put("var1", new Var("var1", "value1", ""));
        field.hVars.put("var2", new Var("var2", "value2", ""));
    }

    @Test
    public void testToStringWithVars() {
        String expected = "\t\tkey = testKey\n" +
                          "\t\tproperty = testProperty\n" +
                          "\t\tindexedProperty = testIndexedProperty\n" +
                          "\t\tindexedListProperty = testIndexedListProperty\n" +
                          "\t\tdepends = testDepends\n" +
                          "\t\tpage = 1\n" +
                          "\t\tfieldOrder = 2\n" +
                          "\t\tVars:\n" +
                          "\t\t\tvar1=value1\n" +
                          "\t\t\tvar2=value2\n";
        assertEquals(expected, field.toString());
    }

    @Test
    public void testToStringWithoutVars() {
        field.hVars = null;
        String expected = "\t\tkey = testKey\n" +
                          "\t\tproperty = testProperty\n" +
                          "\t\tindexedProperty = testIndexedProperty\n" +
                          "\t\tindexedListProperty = testIndexedListProperty\n" +
                          "\t\tdepends = testDepends\n" +
                          "\t\tpage = 1\n" +
                          "\t\tfieldOrder = 2\n";
        assertEquals(expected, field.toString());
    }
}
