
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Nation_flagTest {
    private Nation nation;

    @BeforeEach
    public void setUp() {
        nation = new Nation(new BaseProviders());
    }

    @Test
    void testFlag() {
        String flag = nation.flag();
        assertThat(flag).isNotBlank();
        assertThat(flag).isInstanceOf(String.class);
    }
}
