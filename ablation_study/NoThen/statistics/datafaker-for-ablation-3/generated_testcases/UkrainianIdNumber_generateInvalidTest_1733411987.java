
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UkrainianIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        String dob = DateTimeFormatter.ofPattern("yyyyMMdd").format(birthday);
        String numbers = "1234";
        int multiplied = Utils.multiply(dob + numbers, new int[]{7, 3, 1, 7, 3, 1, 7, 3, 1, 7, 3, 1});
        int checksum = (multiplied + 1) % 10;
        String expected = dob + "-" + numbers + checksum;

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.numerify("####")).thenReturn(numbers);

        UkrainianIdNumber ukrainianIdNumber = new UkrainianIdNumber();

        // When
        String result = ukrainianIdNumber.generateInvalid(faker);

        // Then
        assertEquals(expected, result);
    }
}
