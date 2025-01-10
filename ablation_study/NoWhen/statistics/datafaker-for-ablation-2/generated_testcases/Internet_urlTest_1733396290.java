
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Internet_urlTest {

    @Test
    public void testUrl() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextRandomBytes(6)).thenReturn(new byte[]{0, 1, 2, 3, 4, 5});

        Internet internet = new Internet(faker);

        // When
        String result = internet.url();

        // Then
        assertNotNull(result);
    }
}
