
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

public class MoldovanIdNumber_generateValidTest {
    private MoldovanIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MoldovanIdNumber();
        faker = new Faker();
    }

    @Test
    void testGenerateValid() {
        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber personIdNumber = generator.generateValid(faker, request);

        String basePart = generator.basePart(faker, personIdNumber.getBirthday());
        char expectedChecksum = generator.checksum(basePart);

        assertThat(personIdNumber.getIdNumber()).isEqualTo(basePart + expectedChecksum);
        assertThat(personIdNumber.getBirthday()).isNotNull();
        assertThat(personIdNumber.getGender()).isNotNull();
    }

    @Test
    void testBasePart() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        String basePart = generator.basePart(faker, birthday);

        assertThat(basePart).startsWith("2");
        assertThat(basePart).hasSize(12);
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("293270095431")).isEqualTo('9');
        assertThat(generator.checksum("201403700084")).isEqualTo('3');
        assertThat(generator.checksum("099220624701")).isEqualTo('8');
        assertThat(generator.checksum("200504212980")).isEqualTo('9');
        assertThat(generator.checksum("200504401269")).isEqualTo('3');
        assertThat(generator.checksum("200201100696")).isEqualTo('1');
        assertThat(generator.checksum("200403612722")).isEqualTo('9');
    }
}
