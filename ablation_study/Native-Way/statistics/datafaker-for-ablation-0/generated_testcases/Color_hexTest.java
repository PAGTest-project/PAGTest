
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Color_hexTest {
    private Color color;

    @BeforeEach
    public void setUp() {
        color = new Color(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    void testHexWithHashSign() {
        assertThat(color.hex(true)).matches("^#[0-9A-F]{6}$");
    }

    @Test
    void testHexNoHashSign() {
        assertThat(color.hex(false)).matches("^[0-9A-F]{6}$");
    }
}
