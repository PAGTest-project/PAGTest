
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
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
                        return 64; // Fixed value for predictable test
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

        String cidr = internet.ipV6Cidr();
        assertNotNull(cidr);
        assertTrue(cidr.matches("([0-9a-f]{0,4}:){2,7}[0-9a-f]{0,4}/65"));
    }
}
