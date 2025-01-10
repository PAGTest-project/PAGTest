
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Aws_albTargetGroupARNTest {

    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public FakeValuesService fakeValuesService() {
                return new FakeValuesService(new Locale("en"), new RandomService());
            }
        };
        aws = new Aws(faker);
    }

    @Test
    public void testAlbTargetGroupARN() {
        String arn = aws.albTargetGroupARN();
        assertTrue(arn.matches("^arn:aws:elasticloadbalancing:[a-z0-9-]+:[0-9]{12}:targetgroup/[a-z0-9]+/[a-f0-9]{16}$"));
    }
}
