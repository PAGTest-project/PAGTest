
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_courseTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        educator = new Educator(faker);
    }

    @Test
    void testCourse() {
        assertThat(educator.course()).matches("(\\w+ ?){2,3}");
    }
}
