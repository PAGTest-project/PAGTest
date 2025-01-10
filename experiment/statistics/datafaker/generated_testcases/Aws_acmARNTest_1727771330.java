
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_acmARNTest {

    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        aws = new Aws(faker);
    }

    @Test
    void testAcmARN() {
        String acmARN = aws.acmARN();
        assertThat(acmARN).matches("^arn:aws:acm:\\w+-\\w+-\\d:\\d{10}:certificate/[\\w-]+$");
    }
}
