
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
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.numerify("####")).thenReturn("1234");

        UkrainianIdNumber ukrainianIdNumber = new UkrainianIdNumber();

        // When
        String result = ukrainianIdNumber.generateInvalid(faker);

        // Then
        String expectedDob = DateTimeFormatter.ofPattern("yyyyMMdd").format(birthday);
        String expectedNumbers = "1234";
        int expectedMultiplied = Utils.multiply(expectedDob + expectedNumbers, ukrainianIdNumber.getChecksumWeights());
        int expectedChecksum = (expectedMultiplied + 1) % 10;
        String expectedResult = expectedDob + "-" + expectedNumbers + expectedChecksum;

        assertEquals(expectedResult, result);
    }
}
