
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Company_urlTest {
    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company(new BaseProviders());
    }

    @Test
    void testUrl() {
        String url = company.url();
        assertTrue(url.startsWith("www."));
        assertTrue(url.contains("."));
        assertTrue(url.matches("www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]+"));
    }

    @Test
    void testDomainName() {
        String domainName = company.domainName();
        assertTrue(domainName.matches("[a-zA-Z0-9-]+"));
    }

    @Test
    void testDomainSuffix() {
        String domainSuffix = company.domainSuffix();
        assertTrue(domainSuffix.matches("[a-zA-Z]+"));
    }
}
