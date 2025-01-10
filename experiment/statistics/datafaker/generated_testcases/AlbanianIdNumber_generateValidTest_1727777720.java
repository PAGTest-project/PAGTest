
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
import static org.mockito.Mockito.*;

public class AlbanianIdNumber_generateValidTest {

    private AlbanianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new AlbanianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        Gender gender = Gender.MALE;
        IdNumberRequest request = mock(IdNumberRequest.class);

        when(request.getBirthDate()).thenReturn(birthDate);
        when(request.getGender()).thenReturn(gender);
        when(faker.number().digits(3)).thenReturn("123");

        // When
        PersonIdNumber result = generator.generateValid(faker, request);

        // Then
        assertEquals("900515123", result.getIdNumber().substring(0, 9));
        assertEquals(birthDate, result.getBirthDate());
        assertEquals(gender, result.getGender());
    }
}
