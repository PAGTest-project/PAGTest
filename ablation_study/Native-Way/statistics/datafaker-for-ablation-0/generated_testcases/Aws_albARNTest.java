
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_albARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "us-west-2"; // Example implementation for testing purposes
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        };
        aws = new Aws(faker);
    }

    @Test
    void testAlbARN() {
        assertThat(aws.albARN()).matches("^arn:aws:elasticloadbalancing:\\w+-\\w+-\\d:\\d{10}:loadbalancer/app/[\\w]+/\\w+$");
    }
}
