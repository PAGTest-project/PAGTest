
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UkrainianIdNumber_generateInvalidTest {
    private UkrainianIdNumber ukrainianIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        ukrainianIdNumber = new UkrainianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        String dob = DateTimeFormatter.ofPattern("yyyyMMdd").format(birthday);
        String numbers = "1234";
        int multiplied = Utils.multiply(dob + numbers, UkrainianIdNumber.CHECKSUM_WEIGHTS);
        int checksum = (multiplied + 1) % 10;
        String expected = dob + "-" + numbers + checksum;

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.numerify("####")).thenReturn(numbers);

        String result = ukrainianIdNumber.generateInvalid(faker);
        assertEquals(expected, result);
    }
}
