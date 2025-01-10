
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_secondarySchoolTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                // Mock implementation for testing purposes
                return "MockValue";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        };
        educator = new Educator(faker);
    }

    @Test
    void testSecondarySchool() {
        assertThat(educator.secondarySchool()).matches("(\\w+ ?){1,2} (\\w+ ?){1,2}");
    }
}
