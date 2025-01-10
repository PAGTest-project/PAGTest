
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Aws_albTargetGroupARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        aws = new Aws(faker);
    }

    @Test
    void testRegion() {
        String region = aws.region();
        assertThat(region).matches("^[a-z]{2}-(south|east|north|west|northeast|central|southeast)-\\d$");
    }

    @Test
    void testAccountId() {
        String accountId = aws.accountId();
        assertThat(accountId).matches("^0\\d{9}$");
    }

    @Test
    void testAlbTargetGroupARN() {
        String arn = aws.albTargetGroupARN();
        assertThat(arn).matches("^arn:aws:elasticloadbalancing:[a-z]{2}-(south|east|north|west|northeast|central|southeast)-\\d:0\\d{9}:targetgroup/[a-z0-9]+/[a-f0-9]{16}$");
    }
}
