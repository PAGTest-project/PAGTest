
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BulgarianIdNumber_generateValidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    void testGenerateValid() {
        LocalDate birthDate = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        String basePart = "800320";
        String expectedIdNumber = basePart + "1"; // Assuming checksum for "800320" is "1"

        when(faker.timeAndDate().birthday()).thenReturn(birthDate);
        when(faker.gender().binaryTypes()).thenReturn(gender.toString());

        IdNumberRequest request = new IdNumberRequest(0, 0, null); // Provide required parameters
        PersonIdNumber result = generator.generateValid(faker, request);

        assertThat(result.getIdNumber()).isEqualTo(expectedIdNumber);
        assertThat(result.getBirthday()).isEqualTo(birthDate);
        assertThat(result.getGender()).isEqualTo(gender);
    }

    @Test
    void testGenerateInvalid() {
        LocalDate birthDate = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        String basePart = "800320";
        String expectedInvalidIdNumber = basePart + "2"; // Assuming invalid checksum for "800320" is "2"

        when(faker.timeAndDate().birthday()).thenReturn(birthDate);
        when(faker.gender().binaryTypes()).thenReturn(gender.toString());

        String result = generator.generateInvalid(faker);

        assertThat(result).isEqualTo(expectedInvalidIdNumber);
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
