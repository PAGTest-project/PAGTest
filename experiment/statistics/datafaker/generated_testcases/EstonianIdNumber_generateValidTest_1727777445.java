
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstonianIdNumber_generateValidTest {

    private EstonianIdNumber estonianIdNumber;
    private BaseProviders faker;
    private IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        estonianIdNumber = new EstonianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String baseDigits = "3900101000";
        String expectedIdNumber = baseDigits + "1"; // Assuming checksum for "3900101000" is "1"

        when(Utils.birthday(faker, request)).thenReturn(birthday);
        when(Utils.gender(faker, request)).thenReturn(gender);
        when(estonianIdNumber.basePart(faker, birthday, gender)).thenReturn(baseDigits);
        when(estonianIdNumber.checksum(baseDigits)).thenReturn(1);

        PersonIdNumber result = estonianIdNumber.generateValid(faker, request);

        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
