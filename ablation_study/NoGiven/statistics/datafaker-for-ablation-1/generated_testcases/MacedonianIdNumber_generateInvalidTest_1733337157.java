
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MacedonianIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        MacedonianIdNumber idNumberGenerator = new MacedonianIdNumber();

        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "01019041001"; // Example basePart

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().numberBetween(0, MacedonianIdNumber.getRegionsSize())).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(1); // Male

        // When
        String result = idNumberGenerator.generateInvalid(faker);

        // Then
        int expectedChecksum = (idNumberGenerator.checksum(basePart) + 1) % 10;
        String expectedInvalidId = basePart + expectedChecksum;
        assertEquals(expectedInvalidId, result);
    }
}
