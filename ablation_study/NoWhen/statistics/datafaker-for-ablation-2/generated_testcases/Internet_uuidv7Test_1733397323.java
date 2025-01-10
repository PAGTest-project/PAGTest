
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.nio.ByteBuffer;
import java.util.UUID;

public class Internet_uuidv7Test {

    @Test
    public void testUuidv7() {
        // Given
        Internet internet = new Internet(null);

        // When
        String result = internet.uuidv7();

        // Then
        assertNotNull(result);
        UUID uuid = UUID.fromString(result);
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
    }
}
