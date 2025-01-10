
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.UUID;

public class Internet_uuidv7Test {

    @Test
    public void testUuidv7() {
        // Given
        Internet internet = new Internet(null);
        long timestamp = 1234567890L;
        long randomBits1 = 9876543210L;
        long randomBits2 = 1234567890L;

        // When
        String uuidv7 = internet.uuidv7();

        // Then
        assertNotNull(uuidv7);
        UUID uuid = UUID.fromString(uuidv7);
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
    }
}
