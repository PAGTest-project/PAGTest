
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Company_urlTest {
    private Company company;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker();
        company = new Company(faker);
    }

    @Test
    void testUrl() {
        String url = company.url();
        assertThat(url).matches("www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]+");
    }
}
