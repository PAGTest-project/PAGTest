
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static net.datafaker.providers.base.PersonIdNumber.Gender.FEMALE;
import static net.datafaker.providers.base.PersonIdNumber.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PolishIdNumber_getTest {

    @Test
    public void testGetWithMaleGender() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = PolishIdNumber.Gender.MALE;

        PolishIdNumber polishIdNumber = new PolishIdNumber();
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        assertEquals(PolishIdNumber.Gender.MALE, polishIdNumber.pickGender(faker, requestedGender));
    }

    @Test
    public void testGetWithFemaleGender() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = PolishIdNumber.Gender.FEMALE;

        PolishIdNumber polishIdNumber = new PolishIdNumber();
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        assertEquals(PolishIdNumber.Gender.FEMALE, polishIdNumber.pickGender(faker, requestedGender));
    }

    @Test
    public void testGetWithAnyGender() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = PolishIdNumber.Gender.ANY;

        when(faker.random().nextInt(2)).thenReturn(0); // Mocking randomGender to return MALE

        PolishIdNumber polishIdNumber = new PolishIdNumber();
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        assertEquals(PolishIdNumber.Gender.MALE, polishIdNumber.pickGender(faker, requestedGender));
    }

    @Test
    public void testGetWithNullGender() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = null;

        when(faker.random().nextInt(2)).thenReturn(1); // Mocking randomGender to return FEMALE

        PolishIdNumber polishIdNumber = new PolishIdNumber();
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        assertEquals(PolishIdNumber.Gender.FEMALE, polishIdNumber.pickGender(faker, requestedGender));
    }
}
