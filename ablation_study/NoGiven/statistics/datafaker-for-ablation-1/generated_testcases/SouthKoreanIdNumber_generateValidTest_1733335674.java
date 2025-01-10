
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SouthKoreanIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders f = Mockito.mock(BaseProviders.class);
        IdNumberRequest request = Mockito.mock(IdNumberRequest.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        String iso = "KR";
        Gender gender = Gender.MALE;

        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(f.nation().isoCountry()).thenReturn(iso);
        when(Utils.gender(f, request)).thenReturn(gender);
        when(f.numerify(Mockito.anyString())).thenReturn("900101-1234567");

        SouthKoreanIdNumber idNumberGenerator = new SouthKoreanIdNumber();

        // When
        PersonIdNumber result = idNumberGenerator.generateValid(f, request);

        // Then
        assertEquals("900101-1234567", result.getId());
        assertEquals(birthday, result.getBirthDate());
        assertEquals(gender, result.getGender());
    }
}
