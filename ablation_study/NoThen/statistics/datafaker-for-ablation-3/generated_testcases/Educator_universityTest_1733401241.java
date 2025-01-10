
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_universityTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                // Mock implementation for testing purposes
                return "MockValue";
            }
        };
        educator = new Educator(faker);
    }

    @Test
    void testUniversity() {
        assertThat(educator.university()).matches("(\\w+ ?){2,3}");
    }
}
