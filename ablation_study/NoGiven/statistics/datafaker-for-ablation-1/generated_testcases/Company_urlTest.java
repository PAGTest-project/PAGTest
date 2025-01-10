
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Company_urlTest {
    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company(new BaseFaker());
    }

    @Test
    void testUrl() {
        String url = company.url();
        assertTrue(url.startsWith("www."));
        assertTrue(url.contains("."));
        assertTrue(url.matches("www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]+"));
    }
}
