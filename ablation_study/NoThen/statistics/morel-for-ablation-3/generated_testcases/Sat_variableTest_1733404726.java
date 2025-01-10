
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Sat_variableTest {
    private Sat sat;

    @BeforeEach
    public void setUp() {
        sat = new Sat();
    }

    @Test
    public void testVariableCreation() {
        Variable variable = sat.variable("x");
        assertNotNull(variable);
        assertEquals("x", variable.name);
        assertEquals(0, variable.id);
    }

    @Test
    public void testVariableRetrieval() {
        Variable variable1 = sat.variable("x");
        Variable variable2 = sat.variable("x");
        assertSame(variable1, variable2);
    }

    @Test
    public void testVariableIdIncrement() {
        Variable variable1 = sat.variable("x");
        Variable variable2 = sat.variable("y");
        assertEquals(0, variable1.id);
        assertEquals(1, variable2.id);
    }

    @Test
    public void testVariableNameUniqueness() {
        Variable variable1 = sat.variable("x");
        Variable variable2 = sat.variable("y");
        assertNotEquals(variable1.name, variable2.name);
    }

    @Test
    public void testVariableIdUniqueness() {
        Variable variable1 = sat.variable("x");
        Variable variable2 = sat.variable("y");
        assertNotEquals(variable1.id, variable2.id);
    }
}
