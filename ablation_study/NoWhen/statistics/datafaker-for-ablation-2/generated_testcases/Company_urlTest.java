
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(url).startsWith("www.");
        assertThat(url).containsPattern("\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$");
    }
}
