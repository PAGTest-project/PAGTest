
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.UUID;
import net.datafaker.service.RandomService;

public class Internet_uuidv7Test {

    @Test
    public void testUuidv7() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextLong()).thenReturn(1234567890L, 9876543210L, 1122334455L);

        Internet internet = new Internet(faker);

        // When
        String result = internet.uuidv7();

        // Then
        assertNotNull(result);
        UUID uuid = UUID.fromString(result);
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
    }
}
