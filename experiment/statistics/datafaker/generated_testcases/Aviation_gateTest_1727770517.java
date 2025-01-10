
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Aviation_gateTest {

    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        aviation = new Aviation(faker);
    }

    @Test
    public void testGatePureNumeric() {
        String gate = aviation.gate();
        assertNotNull(gate);
        assertTrue(gate.matches("\\d+"));
    }

    @Test
    public void testGateAlphanumeric() {
        String gate = aviation.gate();
        assertNotNull(gate);
        assertTrue(gate.matches("[A-Z]\\d+"));
    }
}
