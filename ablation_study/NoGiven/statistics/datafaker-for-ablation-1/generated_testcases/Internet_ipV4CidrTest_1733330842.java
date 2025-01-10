
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV4CidrTest {

    @Test
    public void testIpV4Cidr() {
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public Name name() {
                return null;
            }

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
            public <T> T resolve(String key) {
                return null;
            }

            @Override
            public Context getContext() {
                return null;
            }
        });

        String cidr = internet.ipV4Cidr();
        assertTrue(cidr.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/\\d{1,2}"));
    }
}
