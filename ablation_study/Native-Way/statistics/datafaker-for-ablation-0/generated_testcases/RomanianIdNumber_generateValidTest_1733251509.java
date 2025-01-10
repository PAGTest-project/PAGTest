
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RomanianIdNumber_generateValidTest {
    private RomanianIdNumber impl;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        impl = new RomanianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "1900101012345";
        String expectedIdNumber = basePart + "1"; // Assuming checksum is 1 for simplicity

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender().gender()).thenReturn(gender);
        when(faker.number().numberBetween(1, 47)).thenReturn(1);
        when(faker.number().numberBetween(1, 1_000)).thenReturn(123);

        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber result = impl.generateValid(faker, request);

        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }

    @Test
    public void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "1900101012345";
        String expectedInvalidIdNumber = basePart + "2"; // Assuming invalid checksum is 2 for simplicity

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender().gender()).thenReturn(gender);
        when(faker.number().numberBetween(1, 47)).thenReturn(1);
        when(faker.number().numberBetween(1, 1_000)).thenReturn(123);

        String result = impl.generateInvalid(faker);

        assertEquals(expectedInvalidIdNumber, result);
    }
}
