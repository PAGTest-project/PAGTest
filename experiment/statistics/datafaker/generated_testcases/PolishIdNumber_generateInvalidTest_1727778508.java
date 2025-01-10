
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PolishIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        PersonIdNumber.Gender gender = PersonIdNumber.Gender.MALE;

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().randomDigit()).thenReturn(1, 2, 3);
        when(faker.random().nextInt(5)).thenReturn(2);

        PolishIdNumber polishIdNumber = new PolishIdNumber();

        // When
        String result = polishIdNumber.generateInvalid(faker);

        // Then
        int[] expectedDigits = {1, 9, 0, 1, 0, 1, 1, 2, 3, 5};
        int expectedControlDigit = (10 - (1 + 0 + 3 + (9 + 1 + 5) * 3 + (0 + 2) * 7 + (1 + 3) * 9) % 10) % 10;
        int expectedInvalidControlDigit = (expectedControlDigit + 1) % 10;
        StringBuilder expectedPesel = new StringBuilder();
        for (int digit : expectedDigits) {
            expectedPesel.append(digit);
        }
        expectedPesel.append(expectedInvalidControlDigit);

        assertEquals(expectedPesel.toString(), result);
    }
}
