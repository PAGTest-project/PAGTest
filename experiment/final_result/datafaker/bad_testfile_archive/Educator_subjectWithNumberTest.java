
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Educator_subjectWithNumberTest {
    private Educator educator;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }

            @Override
            public FakeValuesService fakeValuesService() {
                // Dummy implementation to satisfy the abstract method
                return null;
            }
        };
        educator = new Educator(faker);
    }

    @Test
    void testSubjectWithNumber() {
        String result = educator.subjectWithNumber();
        assertThat(result).matches("(\\(?\\w+\\)? ?)+ \\d+");
    }
}
