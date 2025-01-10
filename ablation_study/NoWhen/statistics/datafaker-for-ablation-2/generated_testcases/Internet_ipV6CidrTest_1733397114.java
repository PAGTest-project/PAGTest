
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
        Internet internet = new Internet(new BaseProviders());
        String cidr = internet.ipV6Cidr();

        assertNotNull(cidr);
        assertTrue(cidr.matches("^([0-9a-fA-F]{0,4}:){2,7}[0-9a-fA-F]{0,4}/\\d{1,3}$"));
    }
}
