
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
        IdNumberRequest request = new IdNumberRequest(0, 0, null);
        PersonIdNumber personIdNumber = generator.generateValid(faker, request);

        // Validate the generated ID number
        assertThat(personIdNumber.getIdNumber()).isNotBlank();
        assertThat(personIdNumber.getBirthday()).isNotNull();
        assertThat(personIdNumber.getGender()).isNotNull();

        // Validate the checksum
        String basePart = personIdNumber.getIdNumber().substring(0, personIdNumber.getIdNumber().length() - 1);
        char expectedChecksum = generator.checksum(basePart);
        assertThat(personIdNumber.getIdNumber().charAt(personIdNumber.getIdNumber().length() - 1)).isEqualTo(expectedChecksum);
    }

    @Test
    void testBasePart() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        String basePart = generator.basePart(faker, birthday);

        // Validate the base part format
        assertThat(basePart).hasSize(12);
        assertThat(basePart.charAt(0)).isEqualTo('2');
        assertThat(basePart.substring(1, 4)).isEqualTo("990");
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
