
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Var_cloneTest {

    private Var var;

    @BeforeEach
    public void setUp() {
        var = new Var("testName", "testValue", "testJsType");
    }

    @Test
    public void testCloneSuccess() throws CloneNotSupportedException {
        Var clonedVar = (Var) var.clone();
        assertNotNull(clonedVar);
        assertEquals(var.getName(), clonedVar.getName());
        assertEquals(var.getValue(), clonedVar.getValue());
        assertEquals(var.getJsType(), clonedVar.getJsType());
    }

    @Test
    public void testCloneNotSupported() {
        Var nonCloneableVar = new Var("nonCloneable", "value", "jsType") {
            @Override
            public Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };

        assertThrows(UnsupportedOperationException.class, () -> {
            nonCloneableVar.clone();
        });
    }
}
