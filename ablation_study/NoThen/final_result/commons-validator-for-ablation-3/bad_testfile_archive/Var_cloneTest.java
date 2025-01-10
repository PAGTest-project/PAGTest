
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
        var = new Var("testName", "testValue", "testJsType");
        var.setResource(true);
        var.setBundle("testBundle");
    }

    @Test
    public void testClone() {
        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar, "Cloned object is null.");
        assertEquals(var.getName(), clonedVar.getName(), "Cloned name is wrong");
        assertEquals(var.getValue(), clonedVar.getValue(), "Cloned value is wrong");
        assertEquals(var.getJsType(), clonedVar.getJsType(), "Cloned jsType is wrong");
        assertEquals(var.isResource(), clonedVar.isResource(), "Cloned resource status is wrong");
        assertEquals(var.getBundle(), clonedVar.getBundle(), "Cloned bundle is wrong");
    }

    @Test
    public void testCloneWithDefaultConstructor() {
        Var defaultVar = new Var();
        defaultVar.setName("defaultName");
        defaultVar.setValue("defaultValue");
        defaultVar.setJsType("defaultJsType");
        defaultVar.setResource(false);
        defaultVar.setBundle(null);

        Var clonedDefaultVar = (Var) defaultVar.clone();

        assertNotNull(clonedDefaultVar, "Cloned object is null.");
        assertEquals(defaultVar.getName(), clonedDefaultVar.getName(), "Cloned name is wrong");
        assertEquals(defaultVar.getValue(), clonedDefaultVar.getValue(), "Cloned value is wrong");
        assertEquals(defaultVar.getJsType(), clonedDefaultVar.getJsType(), "Cloned jsType is wrong");
        assertEquals(defaultVar.isResource(), clonedDefaultVar.isResource(), "Cloned resource status is wrong");
        assertEquals(defaultVar.getBundle(), clonedDefaultVar.getBundle(), "Cloned bundle is wrong");
    }

    @Test
    public void testCloneWithResourceFalse() {
        var.setResource(false);
        var.setBundle(null);

        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar, "Cloned object is null.");
        assertEquals(var.getName(), clonedVar.getName(), "Cloned name is wrong");
        assertEquals(var.getValue(), clonedVar.getValue(), "Cloned value is wrong");
        assertEquals(var.getJsType(), clonedVar.getJsType(), "Cloned jsType is wrong");
        assertEquals(var.isResource(), clonedVar.isResource(), "Cloned resource status is wrong");
        assertEquals(var.getBundle(), clonedVar.getBundle(), "Cloned bundle is wrong");
    }
}
