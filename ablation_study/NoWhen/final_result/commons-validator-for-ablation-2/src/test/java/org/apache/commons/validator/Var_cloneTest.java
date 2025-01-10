
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Var_cloneTest {

    private Var var;

    @BeforeEach
    public void setUp() {
        var = new Var("testName", "testValue", "testJsType");
    }

    @Test
    public void testClone() {
        Var clonedVar = (Var) var.clone();

        assertNotNull(clonedVar, "Cloned object should not be null.");
        assertEquals(var.getName(), clonedVar.getName(), "Cloned object should have the same name.");
        assertEquals(var.getValue(), clonedVar.getValue(), "Cloned object should have the same value.");
        assertEquals(var.getJsType(), clonedVar.getJsType(), "Cloned object should have the same jsType.");
        assertEquals(var.toString(), clonedVar.toString(), "Cloned object should have the same string representation.");
    }
}
