
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import net.datafaker.service.RandomService;

public class Internet_urlTest {

    private Internet internet;
    private BaseProviders faker;
    private RandomService randomService;

    @BeforeEach
    public void setup() {
        faker = Mockito.mock(BaseProviders.class);
        randomService = Mockito.mock(RandomService.class);
        internet = new Internet(faker);

        when(faker.random()).thenReturn(randomService);
        when(faker.lorem()).thenReturn(Mockito.mock(Lorem.class));
    }

    @Test
    public void testUrl() {
        // Given
        byte[] bts = new byte[]{0, 1, 2, 3, 4, 5};
        when(randomService.nextRandomBytes(6)).thenReturn(bts);

        // When
        String result = internet.url();

        // Then
        assertNotNull(result);
    }
}
