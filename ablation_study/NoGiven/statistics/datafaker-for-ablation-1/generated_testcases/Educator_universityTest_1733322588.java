
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
        String university = educator.university();
        assertThat(university).matches("(\\w+ ?){1,2} (\\w+ ?){1,2}");
    }

    @Test
    void testSecondarySchool() {
        String secondarySchool = educator.secondarySchool();
        assertThat(secondarySchool).matches("(\\w+ ?){1,2} (\\w+ ?){1,2}");
    }

    @Test
    void testCampus() {
        assertThat(educator.campus()).matches("(\\w+ ?){1,2} Campus");
    }
}
