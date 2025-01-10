
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

public class MacedonianIdNumber_generateValidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    void generateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "010190041001";
        String expectedIdNumber = basePart + "6"; // checksum for basePart is 6

        when(faker.dateAndTime().birthday()).thenReturn(birthday);
        when(faker.gender().binaryTypes()).thenReturn(gender.toString());
        when(faker.number().numberBetween(0, 9)).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(1);

        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber result = generator.generateValid(faker, request);

        assertThat(result.getIdNumber()).isEqualTo(expectedIdNumber);
        assertThat(result.getBirthday()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(gender);
    }
}
