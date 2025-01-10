
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV4CidrTest {

    @Test
    public void testIpV4Cidr() {
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public int nextInt(int bound) {
                        return 15; // Fixed value to ensure predictable output
                    }
                };
            }

            @Override
            public Name name() {
                return new Name() {
                    @Override
                    public String firstName() {
                        return "192";
                    }

                    @Override
                    public String lastName() {
                        return "168";
                    }
                };
            }
        });

        String cidr = internet.ipV4Cidr();
        assertTrue(cidr.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/\\d{1,2}"));
    }
}
