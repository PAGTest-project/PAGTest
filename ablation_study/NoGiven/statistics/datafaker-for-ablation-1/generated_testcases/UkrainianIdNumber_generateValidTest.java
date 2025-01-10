
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UkrainianIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumber.IdNumberRequest request = Mockito.mock(IdNumber.IdNumberRequest.class);

        LocalDate birthday = LocalDate.of(1990, 1, 1);
        String dob = DateTimeFormatter.ofPattern("yyyyMMdd").format(birthday);
        String numbers = "1234";
        int checksum = Utils.multiply(dob + numbers, UkrainianIdNumber.CHECKSUM_WEIGHTS) % 10;
        String expectedUnzr = dob + "-" + numbers + checksum;

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.numerify("####")).thenReturn(numbers);
        when(Utils.gender(faker, request)).thenReturn(PersonIdNumber.Gender.MALE);

        UkrainianIdNumber ukrainianIdNumber = new UkrainianIdNumber();

        // When
        PersonIdNumber result = ukrainianIdNumber.generateValid(faker, request);

        // Then
        assertEquals(expectedUnzr, result.getIdNumber());
        assertEquals(birthday, result.getBirthDate());
        assertEquals(PersonIdNumber.Gender.MALE, result.getGender());
    }
}
