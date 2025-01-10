
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String cidr = internet.ipV6Cidr();
        assertTrue(cidr.matches("([0-9a-f]{1,4}:){7}[0-9a-f]{1,4}/\\d{1,3}"));
    }
}
