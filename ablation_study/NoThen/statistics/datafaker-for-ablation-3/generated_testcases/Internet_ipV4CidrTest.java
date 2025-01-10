
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV4CidrTest {

    @Test
    public void testIpV4Cidr() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String cidr = internet.ipV4Cidr();
        assertTrue(cidr.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/\\d{1,2}"));
    }
}
