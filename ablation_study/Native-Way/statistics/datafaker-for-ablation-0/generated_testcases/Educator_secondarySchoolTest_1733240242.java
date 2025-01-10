
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_secondarySchoolTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        educator = new Educator(faker);
    }

    @Test
    void testSecondarySchool() {
        assertThat(educator.secondarySchool()).matches("(\\w+ ?){1,2} (\\w+ ?){1,2}");
    }
}
