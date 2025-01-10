
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
                // Implementation of the abstract method
            }
        });
    }

    @Test
    void testAcmARN() {
        String acmARN = aws.acmARN();
        assertTrue(acmARN.startsWith("arn:aws:acm:"));
        assertTrue(acmARN.contains(":certificate/"));
    }

    @Test
    void testAcmARNRegion() {
        String acmARN = aws.acmARN();
        String region = aws.region();
        assertTrue(acmARN.contains(region));
    }

    @Test
    void testAcmARNAccountId() {
        String acmARN = aws.acmARN();
        String accountId = aws.accountId();
        assertTrue(acmARN.contains(accountId));
    }
}
