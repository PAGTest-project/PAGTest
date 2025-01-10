
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseFaker_getFakerTest {

    @Test
    void testGetFaker() {
        BaseFaker baseFaker = new BaseFaker();
        ProviderRegistration result = baseFaker.getFaker();
        assertEquals(baseFaker, result);
    }
}
