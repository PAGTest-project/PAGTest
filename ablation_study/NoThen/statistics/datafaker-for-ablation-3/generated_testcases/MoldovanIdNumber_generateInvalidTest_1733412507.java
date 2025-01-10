
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
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
    void testGenerateInvalid() {
        String basePart = generator.basePart(faker, faker.date().birthday().toLocalDate());
        char expectedChecksum = generator.checksum(basePart);
        String invalidIdNumber = generator.generateInvalid(faker);
        assertThat(invalidIdNumber).endsWith(String.valueOf((expectedChecksum + 1) % 10));
    }

    @Test
    void testBasePart() {
        LocalDate birthday = faker.date().birthday().toLocalDate();
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
