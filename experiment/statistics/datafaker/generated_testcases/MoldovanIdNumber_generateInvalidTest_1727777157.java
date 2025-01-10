
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoldovanIdNumber_generateInvalidTest {
    private MoldovanIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MoldovanIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateInvalid() {
        String basePart = generator.basePart(faker, faker.timeAndDate().birthday());
        char expectedChecksum = generator.checksum(basePart);
        String expectedInvalidId = basePart + ((expectedChecksum + 1) % 10);

        String actualInvalidId = generator.generateInvalid(faker);

        assertEquals(expectedInvalidId, actualInvalidId);
    }
}
