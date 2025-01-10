
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        var.setResource(true);
        var.setBundle("testBundle");
        var.setJsType("testJsType");

        String expected = "Var: name=testName  value=testValue  resource=true  bundle=testBundle  jsType=testJsType\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithoutResource() {
        var.setName("testName");
        var.setValue("testValue");
        var.setResource(false);
        var.setJsType("testJsType");

        String expected = "Var: name=testName  value=testValue  resource=false  jsType=testJsType\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        var.setName(null);
        var.setValue(null);
        var.setResource(false);
        var.setJsType(null);

        String expected = "Var: name=null  value=null  resource=false  jsType=null\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithEmptyValues() {
        var.setName("");
        var.setValue("");
        var.setResource(false);
        var.setJsType("");

        String expected = "Var: name=  value=  resource=false  jsType=\n";
        assertEquals(expected, var.toString());
    }
}
