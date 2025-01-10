
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Internet_uuidv7Test {

    @Test
    public void testUuidv7() {
        Internet internet = new Internet(null); // Assuming faker is not needed for this test

        // Given
        long timestamp = 1234567890L;
        long randomBits1 = 9876543210L;
        long randomBits2 = 1234567890L;

        // When
        String result = internet.uuidv7();

        // Then
        assertNotNull(result);
        UUID uuid = UUID.fromString(result);
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
    }
}
