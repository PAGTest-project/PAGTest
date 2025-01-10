
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aviation_gateTest {
    private Aviation aviation;

    @BeforeEach
    public void setUp() {
        aviation = new Aviation(new Faker());
    }

    @Test
    void gate_pureNumeric() {
        String gate = aviation.gate();
        assertThat(gate).matches("\\d{1,3}");
    }

    @Test
    void gate_alphanumeric() {
        String gate = aviation.gate();
        assertThat(gate).matches("[A-Z]\\d{1,3}");
    }
}
