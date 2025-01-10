
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aviation_gateTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        aviation = new Aviation(new BaseProviders());
    }

    @Test
    void testGatePureNumeric() {
        String gate = aviation.gate();
        assertThat(gate).matches("\\d{1,3}");
    }

    @Test
    void testGateAlphanumeric() {
        String gate = aviation.gate();
        assertThat(gate).matches("[A-Z]\\d{1,3}");
    }

    @Test
    void testGateContextualDependency() {
        String airport = aviation.airport();
        String gate = aviation.gate();
        assertThat(gate).isNotBlank();
    }

    @Test
    void testGateFunctionalSimilarity() {
        String flight = aviation.flight();
        String gate = aviation.gate();
        assertThat(gate).isNotBlank();
    }

    @Test
    void testGateContextualSimilarity() {
        String airline = aviation.airline();
        String gate = aviation.gate();
        assertThat(gate).isNotBlank();
    }
}
