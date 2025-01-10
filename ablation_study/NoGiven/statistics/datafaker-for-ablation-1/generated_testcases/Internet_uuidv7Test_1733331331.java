
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.UUID;

public class Internet_uuidv7Test {

    @Test
    public void testUuidv7() {
        // Given
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public long nextLong() {
                        return 1234567890L; // Fixed timestamp for reproducibility
                    }
                };
            }
        });

        // When
        String uuidv7 = internet.uuidv7();

        // Then
        UUID uuid = UUID.fromString(uuidv7);
        assertEquals(7, uuid.version());
        assertTrue(uuid.variant() == 2); // IETF variant
    }
}
