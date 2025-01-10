
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
        Internet internet = new Internet(new BaseProviders());
        String cidr = internet.ipV6Cidr();
        assertTrue(cidr.matches("([0-9a-f]{1,4}:){7}[0-9a-f]{1,4}/\\d{1,3}"));
    }
}
