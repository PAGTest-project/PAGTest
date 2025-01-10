
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aws_albTargetGroupARNTest {
    private Aws aws;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        aws = new Aws(faker);
    }

    @Test
    public void testAlbTargetGroupARN() {
        String expectedPrefix = "arn:aws:elasticloadbalancing:";
        String region = aws.region();
        String accountId = aws.accountId();
        String appName = aws.appName();
        String randHex = aws.randHex();

        String expectedARN = expectedPrefix + region + ":" + accountId + ":targetgroup/" + appName + "/" + randHex;
        String actualARN = aws.albTargetGroupARN();

        assertEquals(expectedARN, actualARN);
    }

    private static class Aws extends AbstractProvider<BaseProviders> {

        private final Text.TextRuleConfig configForRoute53ZoneId;

        protected Aws(BaseProviders faker) {
            super(faker);
            configForRoute53ZoneId = Text.TextSymbolsBuilder.builder()
                                     .with(EN_UPPERCASE).len(21).build();
        }

        public String region() {
            return resolve("aws.regions");
        }

        public String service() {
            return resolve("aws.services");
        }

        public String accountId() {
            return faker.numerify("0#########");
        }

        public String acmARN() {
            return "arn:aws:acm:" +
                region() +
                ":" +
                accountId() +
                ":certificate/" +
                faker.internet().uuid();
        }

        public String albARN() {
            return "arn:aws:elasticloadbalancing:" +
                region() +
                ":" +
                accountId() +
                ":loadbalancer/app/" +
                appName() +
                "/" +
                randHex();
        }

        public String albTargetGroupARN() {
            return "arn:aws:elasticloadbalancing:" +
                region() +
                ":" +
                accountId() +
                ":targetgroup/" +
                appName() +
                "/" +
                randHex();
        }

        public String route53ZoneId() {
            return faker.text().text(configForRoute53ZoneId);
        }

        public String securityGroupId() {
            return "sg-" + randHex(17);
        }

        public String subnetId() {
            return "subnet-" + randHex(17);
        }

        public String vpcId() {
            return "vpc-" + randHex(17);
        }

        public String appName() {
            return faker.app().name().toLowerCase().replaceAll("\\W+", "");
        }

        public String randHex(int length) {
            return faker.random().hex(length, false);
        }

        public String randHex() {
            return randHex(16);
        }
    }
}
