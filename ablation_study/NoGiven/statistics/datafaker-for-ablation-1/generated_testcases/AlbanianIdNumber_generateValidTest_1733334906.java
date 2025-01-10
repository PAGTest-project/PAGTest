
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

public class AlbanianIdNumber_generateValidTest {

    private BaseProviders faker;
    private IdNumberRequest request;
    private AlbanianIdNumber idNumberGenerator;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumberRequest.class);
        idNumberGenerator = new AlbanianIdNumber();
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        Gender gender = Gender.FEMALE;
        String basePart = "90550515123";
        String expectedIdNumber = basePart + "A";

        when(Utils.birthday(faker, request)).thenReturn(birthDate);
        when(Utils.gender(faker, request)).thenReturn(gender);
        when(faker.number().digits(3)).thenReturn("123");

        PersonIdNumber result = idNumberGenerator.generateValid(faker, request);

        assertEquals(expectedIdNumber, result.getId());
        assertEquals(birthDate, result.getBirthDate());
        assertEquals(gender, result.getGender());
    }
}
