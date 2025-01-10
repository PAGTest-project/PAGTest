
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstonianIdNumber_generateValidTest {

    private BaseProviders faker;
    private EstonianIdNumber estonianIdNumber;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        estonianIdNumber = new EstonianIdNumber();
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String baseDigits = "3900101000";
        String expectedIdNumber = baseDigits + "1"; // Assuming checksum is 1 for simplicity

        when(faker.number().digits(3)).thenReturn("000");
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(net.datafaker.providers.base.Gender.MALE);

        // When
        PersonIdNumber result = estonianIdNumber.generateValid(faker, new IdNumberRequest(0, 0, Gender.MALE));

        // Then
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }

    @Test
    public void testGenerateValidWithFemale() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.FEMALE;
        String baseDigits = "4900101000";
        String expectedIdNumber = baseDigits + "1"; // Assuming checksum is 1 for simplicity

        when(faker.number().digits(3)).thenReturn("000");
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(net.datafaker.providers.base.Gender.FEMALE);

        // When
        PersonIdNumber result = estonianIdNumber.generateValid(faker, new IdNumberRequest(0, 0, Gender.FEMALE));

        // Then
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
