
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_albARNTest {

    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        aws = new Aws(faker);
    }

    @Test
    void testAlbARN() {
        String albARN = aws.albARN();
        assertThat(albARN).matches("^arn:aws:elasticloadbalancing:\\w+-\\w+-\\d:\\d{10}:loadbalancer/app/[\\w]+/\\w+$");
    }
}
