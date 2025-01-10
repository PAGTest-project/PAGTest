
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SingaporeIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumberRequest request = Mockito.mock(IdNumberRequest.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        boolean citizen = true;
        Gender gender = Gender.MALE;

        when(Utils.birthday(faker, request)).thenReturn(birthDate);
        when(faker.bool().bool()).thenReturn(citizen);
        when(Utils.gender(faker, request)).thenReturn(gender);

        // When
        SingaporeIdNumber singaporeIdNumber = new SingaporeIdNumber();
        PersonIdNumber result = singaporeIdNumber.generateValid(faker, request);

        // Then
        assertNotNull(result);
    }
}
