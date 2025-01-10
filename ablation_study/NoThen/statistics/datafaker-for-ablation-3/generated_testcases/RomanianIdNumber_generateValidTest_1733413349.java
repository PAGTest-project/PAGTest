
package net.datafaker.idnumbers;

import net.datafaker.Faker;
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
        // Given
        LocalDate birthday = LocalDate.of(1990, 8, 19);
        Gender gender = Gender.MALE;
        IdNumberRequest request = mock(IdNumberRequest.class);

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender().binaryTypes()).thenReturn(gender.toString());

        // When
        PersonIdNumber result = impl.generateValid(faker, request);

        // Then
        assertEquals(gender, result.getGender());
        assertEquals(birthday, result.getBirthday());
        assertEquals(13, result.getIdNumber().length()); // Assuming the ID number should be 13 characters long
    }

    @Test
    public void testChecksum() {
        assertEquals(1, impl.checksum("198081945678"));
        assertEquals(4, impl.checksum("293052637289"));
    }
}
