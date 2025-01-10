
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UkrainianIdNumber_generateValidTest {

    private UkrainianIdNumber ukrainianIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        ukrainianIdNumber = new UkrainianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        String dob = DateTimeFormatter.ofPattern("yyyyMMdd").format(birthday);
        String numbers = "1234";
        int checksum = 5; // Calculated checksum for the given dob and numbers

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.numerify("####")).thenReturn(numbers);

        IdNumber.IdNumberRequest request = Mockito.mock(IdNumber.IdNumberRequest.class);
        PersonIdNumber result = ukrainianIdNumber.generateValid(faker, request);

        String expectedUnzr = dob + "-" + numbers + checksum;
        assertEquals(expectedUnzr, result.getIdNumber());
        assertEquals(birthday, result.getBirthDate());
    }
}
