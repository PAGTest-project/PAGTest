
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Var_toStringTest {

    private Var var;

    @BeforeEach
    public void setUp() {
        var = new Var();
    }

    @Test
    public void testToStringWithResource() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("string");
        var.setResource(true);
        var.setBundle("testBundle");

        String expected = "Var: name=testName  value=testValue  resource=true  bundle=testBundle  jsType=string\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithoutResource() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("string");
        var.setResource(false);

        String expected = "Var: name=testName  value=testValue  resource=false  jsType=string\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        var.setName(null);
        var.setValue(null);
        var.setJsType(null);
        var.setResource(false);

        String expected = "Var: name=null  value=null  resource=false  jsType=null\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithEmptyValues() {
        var.setName("");
        var.setValue("");
        var.setJsType("");
        var.setResource(false);

        String expected = "Var: name=  value=  resource=false  jsType=\n";
        assertEquals(expected, var.toString());
    }
}
