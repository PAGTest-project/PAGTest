
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

public class RomanianIdNumber_generateValidTest {
    private RomanianIdNumber romanianIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        romanianIdNumber = new RomanianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "19000101123";
        String expectedIdNumber = basePart + "1"; // Assuming checksum is 1 for simplicity

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender().gender()).thenReturn(gender);
        when(faker.number().numberBetween(1, 47)).thenReturn(1);
        when(faker.number().numberBetween(1, 1_000)).thenReturn(123);

        IdNumberRequest request = new IdNumberRequest();

        // When
        PersonIdNumber result = romanianIdNumber.generateValid(faker, request);

        // Then
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
