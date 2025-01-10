
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
    public void testToStringWithAllFields() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("string");
        var.setResource(true);
        var.setBundle("testBundle");

        String expected = "Var: name=testName  value=testValue  resource=true  bundle=testBundle  jsType=string\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithoutBundle() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("int");
        var.setResource(false);

        String expected = "Var: name=testName  value=testValue  resource=false  jsType=int\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithNullJsType() {
        var.setName("testName");
        var.setValue("testValue");
        var.setResource(false);

        String expected = "Var: name=testName  value=testValue  resource=false  jsType=null\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithEmptyFields() {
        var.setName("");
        var.setValue("");
        var.setJsType("");
        var.setResource(false);

        String expected = "Var: name=  value=  resource=false  jsType=\n";
        assertEquals(expected, var.toString());
    }

    @Test
    public void testToStringWithResourceTrueButNoBundle() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("regexp");
        var.setResource(true);

        String expected = "Var: name=testName  value=testValue  resource=true  jsType=regexp\n";
        assertEquals(expected, var.toString());
    }
}
