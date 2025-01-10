
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aws_albARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "resolvedValue";
            }
        };
        aws = new Aws(faker);
    }

    @Test
    public void testAlbARN() {
        String expectedPrefix = "arn:aws:elasticloadbalancing:";
        String region = aws.region();
        String accountId = aws.accountId();
        String appName = aws.appName();
        String randHex = aws.randHex();

        String expectedARN = expectedPrefix + region + ":" + accountId + ":loadbalancer/app/" + appName + "/" + randHex;
        String actualARN = aws.albARN();

        assertEquals(expectedARN, actualARN);
    }
}
