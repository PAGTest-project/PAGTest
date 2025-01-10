
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_secondarySchoolTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            // Provide a concrete implementation for the abstract methods if needed
        };
        educator = new Educator(faker);
    }

    @Test
    void testSecondarySchool() {
        String secondarySchool = educator.secondarySchool();
        assertThat(secondarySchool).matches("(\\w+ ?){2,3}");
    }
}
