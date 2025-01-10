
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PolishIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumberRequest request = Mockito.mock(IdNumberRequest.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        PersonIdNumber.Gender gender = PersonIdNumber.Gender.MALE;

        when(Utils.birthday(faker, request)).thenReturn(birthday);
        when(Utils.gender(faker, request)).thenReturn(gender);

        PolishIdNumber polishIdNumber = new PolishIdNumber();

        // When
        PersonIdNumber result = polishIdNumber.generateValid(faker, request);

        // Then
        assertNotNull(result);
    }
}
