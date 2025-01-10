
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
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        IdNumberRequest request = mock(IdNumberRequest.class);

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(Gender.MALE);
        when(faker.number().numberBetween(0, 9)).thenReturn(0);

        // When
        PersonIdNumber result = generator.generateValid(faker, request);

        // Then
        assertThat(result.getIdNumber()).startsWith("010190041000");
        assertThat(result.getBirthday()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(gender);
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("010100650000")).isEqualTo(6);
        assertThat(generator.checksum("923456789012")).isEqualTo(4);
    }
}
