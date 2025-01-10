
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public int nextInt(int bound) {
                        return 1; // Ensure the CIDR prefix is always 1
                    }
                };
            }
        });

        String cidr = internet.ipV6Cidr();
        assertTrue(cidr.matches("^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}/1$"));
    }
}
