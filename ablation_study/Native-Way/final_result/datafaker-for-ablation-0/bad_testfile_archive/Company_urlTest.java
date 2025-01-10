
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Company_urlTest {
    private Company company;

    @BeforeEach
    public void setUp() {
        BaseFaker baseFaker = new BaseFaker();
        company = new Company(baseFaker);
    }

    @Test
    void testUrl() {
        String url = company.url();
        assertTrue(url.startsWith("www."));
        assertTrue(url.endsWith(".com") || url.endsWith(".net") || url.endsWith(".org"));
    }

    @Test
    void testUrlDomainName() {
        String domainName = company.domainName();
        String url = company.url();
        assertTrue(url.contains(domainName));
    }

    @Test
    void testUrlDomainSuffix() {
        String domainSuffix = company.domainSuffix();
        String url = company.url();
        assertTrue(url.endsWith(domainSuffix));
    }
}
