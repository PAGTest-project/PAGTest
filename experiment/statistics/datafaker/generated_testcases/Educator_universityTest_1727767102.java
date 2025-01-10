
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_universityTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        educator = new Educator(faker);
    }

    @Test
    void testUniversity() {
        String university = educator.university();
        assertThat(university).matches("(\\(?\\w+\\)? ?){3,6}");
    }
}
