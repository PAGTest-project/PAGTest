
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

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation not needed for this test
            }
        };
        aws = new Aws(faker);
    }

    @Test
    public void testAlbARN() {
        String expectedRegion = aws.region();
        String expectedAccountId = aws.accountId();
        String expectedAppName = aws.appName();
        String expectedRandHex = aws.randHex();

        String expectedARN = "arn:aws:elasticloadbalancing:" +
                expectedRegion +
                ":" +
                expectedAccountId +
                ":loadbalancer/app/" +
                expectedAppName +
                "/" +
                expectedRandHex;

        assertEquals(expectedARN, aws.albARN());
    }
}
