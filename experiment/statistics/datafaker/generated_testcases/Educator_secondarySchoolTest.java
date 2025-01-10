
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
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }

            @Override
            public FakeValuesService fakeValuesService() {
                return new FakeValuesService(new Locale("en"), new RandomService());
            }
        };
        educator = new Educator(faker);
    }

    @Test
    void testSecondarySchool() {
        String result = educator.secondarySchool();
        assertThat(result).matches("(\\(?\\w+\\)? ?){2,4}");
    }
}
