
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Var_cloneTest {

    private Var var;

    @BeforeEach
    public void setUp() {
        var = new Var();
    }

    @Test
    public void testCloneWithAllFieldsSet() {
        var.setName("testName");
        var.setValue("testValue");
        var.setJsType("string");
        var.setResource(true);
        var.setBundle("testBundle");

        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar);
        assertEquals("testName", clonedVar.getName());
        assertEquals("testValue", clonedVar.getValue());
        assertEquals("string", clonedVar.getJsType());
        assertTrue(clonedVar.isResource());
        assertEquals("testBundle", clonedVar.getBundle());
    }

    @Test
    public void testCloneWithDefaultFields() {
        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar);
        assertEquals(null, clonedVar.getName());
        assertEquals(null, clonedVar.getValue());
        assertEquals(null, clonedVar.getJsType());
        assertFalse(clonedVar.isResource());
        assertEquals(null, clonedVar.getBundle());
    }

    @Test
    public void testCloneWithPartialFieldsSet() {
        var.setName("testName");
        var.setValue("testValue");

        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar);
        assertEquals("testName", clonedVar.getName());
        assertEquals("testValue", clonedVar.getValue());
        assertEquals(null, clonedVar.getJsType());
        assertFalse(clonedVar.isResource());
        assertEquals(null, clonedVar.getBundle());
    }
}
