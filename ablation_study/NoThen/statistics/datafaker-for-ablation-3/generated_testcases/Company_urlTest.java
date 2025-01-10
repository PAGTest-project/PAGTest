
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Company_urlTest {

    @Test
    public void testUrl() {
        // Given
        Company company = spy(new Company(null));
        doReturn("example").when(company).domainName();
        doReturn("com").when(company).domainSuffix();

        // When
        String result = company.url();

        // Then
        assertEquals("www.example.com", result);
    }
}
