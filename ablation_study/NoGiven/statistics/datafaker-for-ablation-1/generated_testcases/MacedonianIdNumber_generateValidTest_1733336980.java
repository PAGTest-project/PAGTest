
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MacedonianIdNumber_generateValidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateValid() {
        // Mocking dependencies
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        when(faker.dateAndTime().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);
        when(faker.number().numberBetween(0, 9)).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(123);

        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber result = generator.generateValid(faker, request);

        // Assertions
        assertThat(result.getIdNumber()).isEqualTo("0101990411236");
        assertThat(result.getBirthday()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(gender);
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("010100650000")).isEqualTo(6);
        assertThat(generator.checksum("923456789012")).isEqualTo(4);
    }
}
