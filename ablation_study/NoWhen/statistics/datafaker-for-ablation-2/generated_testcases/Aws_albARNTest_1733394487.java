
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_albARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        aws = new Aws(faker);
    }

    @Test
    void testAlbARN() {
        String albARN = aws.albARN();
        assertThat(albARN).matches("^arn:aws:elasticloadbalancing:\\w+-\\w+-\\d:\\d{10}:loadbalancer/app/[\\w\\-]+/[\\w\\-]+$");
    }

    @Test
    void testAlbTargetGroupARN() {
        String albTargetGroupARN = aws.albTargetGroupARN();
        assertThat(albTargetGroupARN).matches("^arn:aws:elasticloadbalancing:\\w+-\\w+-\\d:\\d{10}:targetgroup/[\\w\\-]+/[\\w\\-]+$");
    }

    @Test
    void testAcmARN() {
        String acmARN = aws.acmARN();
        assertThat(acmARN).matches("^arn:aws:acm:\\w+-\\w+-\\d:\\d{10}:certificate/[\\w\\-]+$");
    }

    @Test
    void testRoute53ZoneId() {
        String route53ZoneId = aws.route53ZoneId();
        assertThat(route53ZoneId).matches("^[A-Z]{21}$");
    }
}
