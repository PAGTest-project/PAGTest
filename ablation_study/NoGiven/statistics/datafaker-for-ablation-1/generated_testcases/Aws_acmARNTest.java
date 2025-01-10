
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String acmARN = aws.acmARN();
        assertTrue(acmARN.startsWith("arn:aws:acm:"));
        assertTrue(acmARN.contains(":"));
        assertTrue(acmARN.contains(":certificate/"));
    }
}
