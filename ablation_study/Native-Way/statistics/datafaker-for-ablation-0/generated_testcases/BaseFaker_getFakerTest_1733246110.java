
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseFaker_getFakerTest {
    private BaseFaker faker;
    private Random random;

    @BeforeEach
    final void before() {
        random = new Random();
        faker = new BaseFaker(random);
    }

    @Test
    void testGetFakerReturnsSelf() {
        assertThat(faker.getFaker()).isSameAs(faker);
    }
}
