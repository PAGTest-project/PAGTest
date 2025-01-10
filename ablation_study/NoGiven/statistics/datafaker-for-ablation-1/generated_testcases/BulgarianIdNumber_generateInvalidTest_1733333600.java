
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

public class BulgarianIdNumber_generateInvalidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = new BaseProviders() {
            @Override
            public LocalDate birthday() {
                return LocalDate.of(1980, 3, 20); // Example fixed date for testing
            }
        };
    }

    @Test
    void testGenerateInvalid() {
        String basePart = generator.basePart(faker, faker.timeAndDate().birthday(), Gender.MALE);
        int originalChecksum = generator.checksum(basePart);
        String invalidId = generator.generateInvalid(faker);

        assertThat(invalidId).startsWith(basePart);
        assertThat(invalidId).endsWith(String.valueOf((originalChecksum + 1) % 10));
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("803205603")).isEqualTo(1);
        assertThat(generator.checksum("800101000")).isEqualTo(8);
        assertThat(generator.checksum("750102001")).isEqualTo(8);
        assertThat(generator.checksum("820630876")).isEqualTo(0);
        assertThat(generator.checksum("560628204")).isEqualTo(7);
        assertThat(generator.checksum("752316926")).isEqualTo(3);
        assertThat(generator.checksum("755201000")).isEqualTo(5);
        assertThat(generator.checksum("754201103")).isEqualTo(0);
    }
}
