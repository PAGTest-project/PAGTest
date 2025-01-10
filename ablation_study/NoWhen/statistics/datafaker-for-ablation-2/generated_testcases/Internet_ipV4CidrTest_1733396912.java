
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Internet_ipV4CidrTest {

    @Test
    public void testIpV4Cidr() {
        Internet internet = new Internet(new BaseProviders());
        String cidr = internet.ipV4Cidr();
        
        assertNotNull(cidr);
        assertTrue(cidr.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}/\\d{1,2}"));
    }
}
