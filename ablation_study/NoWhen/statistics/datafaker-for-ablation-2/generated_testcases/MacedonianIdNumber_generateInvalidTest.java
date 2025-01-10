
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

public class MacedonianIdNumber_generateInvalidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = new BaseProviders() {
            @Override
            public LocalDate birthday() {
                return LocalDate.of(1990, 1, 1);
            }

            @Override
            public Gender gender() {
                return Gender.MALE;
            }

            @Override
            public int numberBetween(int min, int max) {
                return 0;
            }
        };
    }

    @Test
    void testGenerateInvalid() {
        String invalidId = generator.generateInvalid(faker);
        String basePart = generator.basePart(faker, faker.birthday(), faker.gender());
        int checksum = generator.checksum(basePart);
        assertThat(invalidId).isEqualTo(basePart + ((checksum + 1) % 10));
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("010100650000")).isEqualTo(6);
        assertThat(generator.checksum("923456789012")).isEqualTo(4);
    }
}
