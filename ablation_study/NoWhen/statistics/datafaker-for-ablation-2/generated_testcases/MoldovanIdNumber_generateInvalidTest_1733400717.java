
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoldovanIdNumber_generateInvalidTest {
    private MoldovanIdNumber generator;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        generator = new MoldovanIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateInvalid() {
        String basePart = generator.basePart(faker, faker.date().birthday().toLocalDate());
        char expectedChecksum = generator.checksum(basePart);
        String invalidIdNumber = generator.generateInvalid(faker);
        assertEquals(basePart + ((expectedChecksum + 1) % 10), invalidIdNumber);
    }
}
