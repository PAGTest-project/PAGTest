
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_acmARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        aws = new Aws(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation not needed for this test
            }
        });
    }

    @Test
    void testAcmARN() {
        assertThat(aws.acmARN()).matches("^arn:aws:acm:\\w+-\\w+-\\d:\\d{10}:certificate/[\\w-]+$");
    }
}
