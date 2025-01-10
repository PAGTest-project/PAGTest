
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
        LocalDate birthday = LocalDate.of(faker.number().numberBetween(1900, 2023), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28));
        String basePart = generator.basePart(faker, birthday);
        char expectedChecksum = generator.checksum(basePart);
        String invalidIdNumber = generator.generateInvalid(faker);
        assertEquals(basePart + ((expectedChecksum + 1) % 10), invalidIdNumber);
    }
}
